/*
 * Copyright (c) 2016 NIBIO <http://www.nibio.no/>. 
 * 
 * This file is part of DeliaRadicumFloralisObservationModel.
 * DeliaRadicumFloralisObservationModel is free software: you can redistribute it and/or modify
 * it under the terms of the NIBIO Open Source License as published by 
 * NIBIO, either version 1 of the License, or (at your option) any
 * later version.
 * 
 * DeliaRadicumFloralisObservationModel is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * NIBIO Open Source License for more details.
 * 
 * You should have received a copy of the NIBIO Open Source License
 * along with DeliaRadicumFloralisObservationModel.  If not, see <http://www.nibio.no/licenses/>.
 * 
 */

package no.nibio.vips.model.deliaradicumfloralisobservationmodel;

import java.util.Date;
import no.nibio.vips.entity.Result;
import no.nibio.vips.model.ModelId;

/**
 * @copyright 2016 <a href="http://www.nibio.no/">NIBIO</a>
 * @author Tor-Einar Skog <tor-einar.skog@nibio.no>
 */
public class DeliaRadicumFloralisObservationModelOldCrop extends DeliaRadicumFloralisObservationModel{

    @Override
    public ModelId getModelId()
    {
        return new ModelId("DELIARFOBO");
    }
    
    
    @Override
    public String getModelName(String language)
    {
        return this.getText("nameOldCrop", language);
    }
    
    @Override
    public String getModelDescription(String language)
    {
        return this.getText("descriptionOldCrop", language);
    }
    
    @Override
    public String getWarningStatusInterpretation(String language)
    {
        return this.getText("statusInterpretationOldCrop", language);
    }
    
    @Override
    protected Integer getEconomicThreshold(Date currentDate)
    {
        return 40;
    }
    
    @Override
    public void setThresholdsInResult(Result result)
    {
        result.setValue(DeliaRadicumFloralisObservationModel.MODEL_ID.toString(), "THRESHOLD_OLD", String.valueOf(DeliaRadicumFloralisObservationModel.DAMAGE_THRESHOLD_OLD_CROP));
    }
}
