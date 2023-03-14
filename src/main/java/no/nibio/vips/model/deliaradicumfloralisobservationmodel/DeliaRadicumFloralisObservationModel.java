/*
 * Copyright (c) 2016 NIBIO <http://www.nibio.no/>. 
 * 
 * This file is part of DeliaRadicumFloralisObservationModel.
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
 * 
 */

package no.nibio.vips.model.deliaradicumfloralisobservationmodel;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;
import no.nibio.vips.entity.ModelConfiguration;
import no.nibio.vips.entity.Result;
import no.nibio.vips.entity.ResultImpl;
import no.nibio.vips.i18n.I18nImpl;
import no.nibio.vips.model.ConfigValidationException;
import no.nibio.vips.model.Model;
import no.nibio.vips.model.ModelExcecutionException;
import no.nibio.vips.model.ModelId;
import no.nibio.vips.observation.Observation;
import no.nibio.vips.observation.ObservationImpl;
import no.nibio.vips.util.ModelUtil;
import no.nibio.vips.util.WeatherUtil;

/**
 * @copyright 2016 <a href="http://www.nibio.no/">NIBIO</a>
 * @author Tor-Einar Skog <tor-einar.skog@nibio.no>
 */
public class DeliaRadicumFloralisObservationModel extends I18nImpl implements Model{
    
    public final static ModelId MODEL_ID = new ModelId("DELIARFOBS");
    protected final static Integer DAMAGE_THRESHOLD_YOUNG_CROP = 14;
    protected final static Integer DAMAGE_THRESHOLD_OLD_CROP = 40;
    protected final static Integer OLD_CROP_AGE_IN_DAYS = 28;
    private final ModelUtil modelUtil;
    private final WeatherUtil weatherUtil;
    
    private ObjectMapper objectMapper;
    
    private List<Observation> pestObservations;
    private TimeZone timeZone;
    private Date startDateGrowth;
    private Date endDateCalculation;
    private final Integer observationMaximumValidDays = 9;
    
    public DeliaRadicumFloralisObservationModel()
    {
        super("no.nibio.vips.model.deliaradicumfloralisobservationmodel.texts");
        this.modelUtil = new ModelUtil();
        this.weatherUtil = new WeatherUtil();
    }

    @Override
    public List<Result> getResult() throws ModelExcecutionException {
        
        List<Result> retVal = new ArrayList<>();
        Calendar cal = Calendar.getInstance(timeZone);
        DecimalFormat dFormat = new DecimalFormat("###.##");
        
        Date currentDate = startDateGrowth;
        boolean firstValidObservationFound = false;
        while(currentDate.compareTo(endDateCalculation) <= 0)
        {
            Result result = new ResultImpl();
            result.setValidTimeStart(currentDate);
            
            Observation latestObs = getObservationValidForDate(currentDate);
            if(latestObs == null && ! firstValidObservationFound)
            {
                result.setWarningStatus(Result.WARNING_STATUS_NO_WARNING);
            }
            else if(latestObs == null)
            {
                result.setWarningStatus(Result.WARNING_STATUS_NO_WARNING_MISSING_DATA);
            }
            else if(this.getPestObservationValue(latestObs.getObservationData()) >= this.getEconomicThreshold(currentDate))
            {
                firstValidObservationFound = true;
                result.setWarningStatus(Result.WARNING_STATUS_HIGH_RISK);
            }
            else
            {
                firstValidObservationFound = true;
                result.setWarningStatus(Result.WARNING_STATUS_NO_RISK);
            }
            
            Observation obsToday = getObservationAtDate(currentDate);
            if(obsToday != null)
            {
                result.setValue(DeliaRadicumFloralisObservationModel.MODEL_ID.toString(), "OBS_AVG", dFormat.format(this.getPestObservationValue(obsToday.getObservationData())));
            }
            // Outsourcing this to enable override for the subclasses
            this.setThresholdsInResult(result);
            
            retVal.add(result);
            // Move forward in time    
            cal.setTime(currentDate);
            cal.add(Calendar.DATE, 1);
            currentDate = cal.getTime();
        }
        return retVal;
    }
    
    public void setThresholdsInResult(Result result)
    {
        result.setValue(DeliaRadicumFloralisObservationModel.MODEL_ID.toString(), "THRESHOLD_YOUNG", String.valueOf(DeliaRadicumFloralisObservationModel.DAMAGE_THRESHOLD_YOUNG_CROP));
        result.setValue(DeliaRadicumFloralisObservationModel.MODEL_ID.toString(), "THRESHOLD_OLD", String.valueOf(DeliaRadicumFloralisObservationModel.DAMAGE_THRESHOLD_OLD_CROP));
    }

    @Override
    public ModelId getModelId() {
        return DeliaRadicumFloralisObservationModel.MODEL_ID;
    }

    @Override
    public String getModelName() {
        return this.getModelName(Model.DEFAULT_LANGUAGE);
    }

    @Override
    public String getModelName(String language) {
        return this.getText("name", language);
    }

    @Override
    public String getLicense() {
        return "Copyright (c) 2016 NIBIO <http://www.nibio.no/>. \n" +
            "\n" +
            "This file is part of DeliaRadicumFloralisObservationModel. \n" + 
            "DeliaRadicumFloralisObservationModel is free software: you can redistribute it and/or modify \n" + 
            "it under the terms of the NIBIO Open Source License as published by \n" + 
            "NIBIO, either version 1 of the License, or (at your option) any \n" +
            "later version. \n" +
            "\n" +
            "DeliaRadicumFloralisObservationModel is distributed in the hope that it will be useful, \n" +
            "but WITHOUT ANY WARRANTY; without even the implied warranty of \n" +
            "MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the \n" +
            "NIBIO Open Source License for more details. \n" +
            "" +
            "You should have received a copy of the NIBIO Open Source License \n" +
            "along with DeliaRadicumFloralisObservationModel.  If not, see <http://www.nibio.no/licenses/>. \n";
    }

    @Override
    public String getCopyright() {
        return "(c) 2016 NIBIO (http://www.nibio.no/). Contact: post@nibio.no";
    }

    @Override
    public String getModelDescription() {
        return this.getModelDescription(Model.DEFAULT_LANGUAGE);
    }

    @Override
    public String getModelDescription(String language) {
        try
        {
            return this.modelUtil.getTextWithBase64EncodedImages(this.getText("description", language), this.getClass());
        }
        catch(IOException ex)
        {
            return this.getText("description", language);
        }
    }

    @Override
    public String getWarningStatusInterpretation() {
        return this.getWarningStatusInterpretation(Model.DEFAULT_LANGUAGE);
    }

    @Override
    public String getWarningStatusInterpretation(String language) {
        return this.getText("statusInterpretation", language);
    }

    @Override
    public String getModelUsage() {
        return this.getModelUsage(Model.DEFAULT_LANGUAGE);
    }

    @Override
    public String getModelUsage(String language) {
        return this.getText("usage", language);
    }

    @Override
    public String getSampleConfig() {
        return  "{\n" +
                "\t\"loginInfo\":{\n" +
                "\t\t\"username\":\"example\",\n" +
                "\t\t\"password\":\"example\"\n" +
                "\t},\n" +
                "\t\"modelId\":\"" + MODEL_ID.toString() + "\",\n" +
                "\t\"configParameters\":{\n" +
                "\t\t\"timeZone\":\"Europe/Oslo\",\n" +
                "\t\t\"startDateGrowth\":\"2012-03-25\",\n" +
                "\t\t\"endDateCalculation\":\"2012-05-25\",\n" +
                "\t\t\"pestObservations\":[\n" +
                "\t\t\t{" +
                "\t\t\t\t\"timeOfObservation\":\"2012-04-20T00:00:00+02:00\",\n" +
                "\t\t\t\t\"observationData\":{\n" +
                "\t\t\t\t\t\"counting01\":3,\n" +
                "\t\t\t\t\t\"counting02\":2,\n" +
                "\t\t\t\t\t\"counting03\":3,\n" +
                "\t\t\t\t\t\"counting04\":4,\n" +
                "\t\t\t\t\t\"counting05\":0,\n" + 
                "\t\t\t\t\t\"counting06\":0,\n" +
                "\t\t\t\t\t\"counting07\":0,\n" +
                "\t\t\t\t\t\"counting08\":0,\n" +
                "\t\t\t\t\t\"counting09\":0,\n" +
                "\t\t\t\t\t\"counting10\":0\n" +
                "\t\t\t\t}\n" +
                "\t\t\t\"},\n" +
                "\t\t\t{\n" +
                "\t\t\t\t\"timeOfObservation\":\"2012-04-25T00:00:00+02:00\"," +
                "\t\t\t\t\"observationData\":{\n" +
                "\t\t\t\t\t\"counting01\":30,\n" +
                "\t\t\t\t\t\"counting02\":18,\n" +
                "\t\t\t\t\t\"counting03\":13,\n" +
                "\t\t\t\t\t\"counting04\":14,\n" +
                "\t\t\t\t\t\"counting05\":25,\n" + 
                "\t\t\t\t\t\"counting06\":20,\n" +
                "\t\t\t\t\t\"counting07\":9,\n" +
                "\t\t\t\t\t\"counting08\":15,\n" +
                "\t\t\t\t\t\"counting09\":21,\n" +
                "\t\t\t\t\t\"counting10\":21\n" +
                "\t\t\t\t}\n" +
                "\t\t\t\"}\n" +
                "\t\t]\n" +
                "\t}\n" +
                "}\n";
    }

    @Override
    public void setConfiguration(ModelConfiguration config) throws ConfigValidationException {
        this.getObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        this.pestObservations = this.getObjectMapper().convertValue(config.getConfigParameter("pestObservations"), new TypeReference<List<Observation>>(){});
        Collections.sort(this.pestObservations);
        // Setting timezone
        this.timeZone = TimeZone.getTimeZone((String) config.getConfigParameter("timeZone"));
        
        //this.startDateAscosporeMaturity = mapper.convertValue(config.getConfigParameter("startDateAscosporeMaturity"), new TypeReference<java.util.Date>(){});
        String[] dateparts;
        try
        {
            dateparts = config.getConfigParameter("startDateGrowth").toString().split("-");
        }
        catch (NullPointerException ex){
            throw new ConfigValidationException("startDateGrowth not set");
        }
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        format.setTimeZone(timeZone);
        try
        {
          
            this.startDateGrowth = format.parse(config.getConfigParameter("startDateGrowth").toString());
            this.endDateCalculation = format.parse(config.getConfigParameter("endDateCalculation").toString());
        }
        catch(ParseException | NullPointerException ex)
        {
            throw new ConfigValidationException("Illegal date format for startDateGrowth: " + config.getConfigParameter("startDateGrowth"));
        }
        
    }

    /**
     * Depends on age of crop
     * @param currentDate
     * @return 
     */
    protected Integer getEconomicThreshold(Date currentDate)
    {
        Calendar cal = Calendar.getInstance(this.timeZone);
        cal.setTime(currentDate);
        cal.add(Calendar.DATE, - DeliaRadicumFloralisObservationModel.OLD_CROP_AGE_IN_DAYS);
        if(this.startDateGrowth.before(cal.getTime()))
        {
            return DeliaRadicumFloralisObservationModel.DAMAGE_THRESHOLD_OLD_CROP;
        }
        else
        {
            return DeliaRadicumFloralisObservationModel.DAMAGE_THRESHOLD_YOUNG_CROP;
        }
    }

    public Observation getObservationValidForDate(Date currentDate) {
        if(this.pestObservations == null || this.pestObservations.isEmpty())
        {
            return null;
        }
        Long currentTime = currentDate.getTime();
        Observation currentObservation = null;
        for(Observation obs:this.pestObservations)
        {
            Long obsTime = this.weatherUtil.normalizeToExactDate(obs.getTimeOfObservation(), timeZone).getTime();
            if( currentTime - obsTime >= 0 // Must be before the current date
                && currentTime - obsTime < this.observationMaximumValidDays * (24 * 3600 * 1000) // Must be maximum [9] days before current date
                && (currentObservation == null || currentTime - obsTime < currentTime - currentObservation.getTimeOfObservation().getTime()) // Must be closer to current date than any other observations
            )
            {
                currentObservation = obs;
            }
        }
        return currentObservation;
    }
    
    /**
     * Parses the JSON and returns the average number
     * @param observationData
     * @return 
     */
    public Double getPestObservationValue(String observationData)
    {
        try
        {
            Map<String, Double> data = this.getObjectMapper().readValue(new ByteArrayInputStream(observationData.getBytes("UTF-8")), new TypeReference<HashMap<String,Double>>() {});
            Double sum = 0.0;
            for(String key:data.keySet())
            {
                sum += data.get(key);
            }
            return sum/data.size();
        }
        catch(IOException | NullPointerException ex)
        {
            return 0.0;
        }
    }
    
    private ObjectMapper getObjectMapper()
    {
        if(this.objectMapper == null)
        {
            this.objectMapper = new ObjectMapper();
        }
        return this.objectMapper;
    }

    public Observation getObservationAtDate(Date currentDate) {
        if(this.pestObservations == null || this.pestObservations.isEmpty())
        {
            return null;
        }
        for(Observation obs:this.pestObservations)
        {
            if(this.weatherUtil.normalizeToExactDate(obs.getTimeOfObservation(), timeZone).compareTo(currentDate) == 0)
            {
                return obs;
            }
        }
        return null;
    }
}
