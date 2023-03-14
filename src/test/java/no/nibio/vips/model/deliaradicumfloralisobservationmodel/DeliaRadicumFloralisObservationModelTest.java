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

import com.fasterxml.jackson.databind.ObjectMapper;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;
import no.nibio.vips.entity.ModelConfiguration;
import no.nibio.vips.entity.Result;
import no.nibio.vips.model.ModelId;
import no.nibio.vips.observation.Observation;
import no.nibio.vips.observation.ObservationImpl;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author treinar
 */
public class DeliaRadicumFloralisObservationModelTest {
    
    public DeliaRadicumFloralisObservationModelTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getResult method, of class DeliaRadicumFloralisObservationModel.
     */
    @Test
    public void testGetResult() throws Exception {
        System.out.println("getResult");
        DeliaRadicumFloralisObservationModel instance = new DeliaRadicumFloralisObservationModel();
        ModelConfiguration config = new ModelConfiguration();
        String tzID="Europe/Oslo";
        TimeZone timeZone = TimeZone.getTimeZone(tzID);
        config.setConfigParameter("timeZone", tzID);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        format.setTimeZone(timeZone);

        config.setConfigParameter("startDateGrowth", "2016-04-01");
        config.setConfigParameter("endDateCalculation", "2016-06-01");
        
        List<Observation> pestObservations = new ArrayList<>();
        ObservationImpl obs1 = new ObservationImpl();
        obs1.setTimeOfObservation(format.parse("2016-04-20"));
        obs1.setObservationData("{\"counting01\":3,\"counting02\":2,\"counting03\":3,\"counting04\":4,\"counting05\":0,\"counting06\":0,\"counting07\":0,\"counting08\":0,\"counting09\":0,\"counting10\":0}");
        pestObservations.add(obs1);
        
        ObservationImpl obs2 = new ObservationImpl();
        obs2.setTimeOfObservation(format.parse("2016-04-24"));
        obs2.setObservationData("{\"counting01\":3,\"counting02\":12,\"counting03\":13,\"counting04\":14,\"counting05\":20,\"counting06\":18,\"counting07\":15,\"counting08\":13,\"counting09\":18,\"counting10\":22}");
        pestObservations.add(obs2);
        
        config.setConfigParameter("pestObservations", pestObservations);
        
        instance.setConfiguration(config);
        List<Result> expResult = null;
        List<Result> result = instance.getResult();
        System.out.println("Number of results returned: " + result.size());
        /*for(Result res:result)
        {
            System.out.println(res);
        }*/
        assertNotNull(result);
    }

    /**
     * Test of getModelId method, of class DeliaRadicumFloralisObservationModel.
     */
    @Test
    public void testGetModelId() {
        System.out.println("getModelId");
        DeliaRadicumFloralisObservationModel instance1 = new DeliaRadicumFloralisObservationModel();
        DeliaRadicumFloralisObservationModel instance2 = new DeliaRadicumFloralisObservationModelYoungCrop();
        DeliaRadicumFloralisObservationModel instance3 = new DeliaRadicumFloralisObservationModelOldCrop();
        ModelId result = instance1.getModelId();
        assertEquals("DELIARFOBS", result.toString());
        result = instance2.getModelId();
        assertEquals("DELIARFOBY", result.toString());
        result = instance3.getModelId();
        assertEquals("DELIARFOBO", result.toString());
        
    }

    /**
     * Test of getModelName method, of class DeliaRadicumFloralisObservationModel.
     */
    @Test
    public void testGetModelName_0args() {
        System.out.println("getModelName");
        DeliaRadicumFloralisObservationModel instance1 = new DeliaRadicumFloralisObservationModel();
        DeliaRadicumFloralisObservationModel instance2 = new DeliaRadicumFloralisObservationModelYoungCrop();
        DeliaRadicumFloralisObservationModel instance3 = new DeliaRadicumFloralisObservationModelOldCrop();
        
        assertNotNull(instance1.getModelName());
        assertNotNull(instance2.getModelName());
        assertNotNull(instance3.getModelName());
        assertNotEquals(instance1.getModelName(), instance2.getModelName());
        assertNotEquals(instance2.getModelName(), instance3.getModelName());
        
    }

    

    /**
     * Test of getLicense method, of class DeliaRadicumFloralisObservationModel.
     */
    @Test
    public void testGetLicense() {
        System.out.println("getLicense");
        DeliaRadicumFloralisObservationModel instance = new DeliaRadicumFloralisObservationModel();
        assertNotNull(instance.getLicense());
    }

    /**
     * Test of getCopyright method, of class DeliaRadicumFloralisObservationModel.
     */
    @Test
    public void testGetCopyright() {
        System.out.println("getCopyright");
        DeliaRadicumFloralisObservationModel instance = new DeliaRadicumFloralisObservationModel();
        assertNotNull(instance.getCopyright());
    }

    /**
     * Test of getModelDescription method, of class DeliaRadicumFloralisObservationModel.
     */
    @Test
    public void testGetModelDescription_0args() {
        System.out.println("getModelDescription");
        DeliaRadicumFloralisObservationModel instance = new DeliaRadicumFloralisObservationModel();
        assertNotNull(instance.getModelDescription());
    }

    
    /**
     * Test of getWarningStatusInterpretation method, of class DeliaRadicumFloralisObservationModel.
     */
    @Test
    public void testGetWarningStatusInterpretation_0args() {
        System.out.println("getWarningStatusInterpretation");
        DeliaRadicumFloralisObservationModel instance = new DeliaRadicumFloralisObservationModel();
        assertNotNull(instance.getWarningStatusInterpretation());
    }

    
    /**
     * Test of getModelUsage method, of class DeliaRadicumFloralisObservationModel.
     */
    @Test
    public void testGetModelUsage_0args() {
        System.out.println("getModelUsage");
        DeliaRadicumFloralisObservationModel instance = new DeliaRadicumFloralisObservationModel();
        assertNotNull(instance.getModelUsage());
    }

    

    /**
     * Test of getSampleConfig method, of class DeliaRadicumFloralisObservationModel.
     */
    @Test
    public void testGetSampleConfig() {
        System.out.println("getSampleConfig");
        DeliaRadicumFloralisObservationModel instance = new DeliaRadicumFloralisObservationModel();
       
        String result = instance.getSampleConfig();
        assertNotNull(result);
    }

    
    
    @Test
    public void testGetPestObservationValue() throws Exception {
        System.out.println("testGetPestObservationValue");
        DeliaRadicumFloralisObservationModel instance = new DeliaRadicumFloralisObservationModel();
        String observationData="{\"counting01\":3,\"counting02\":2,\"counting03\":3,\"counting04\":4,\"counting05\":0,\"counting06\":0,\"counting07\":0,\"counting08\":0,\"counting09\":0,\"counting10\":0}";
        Double expectedResult = 1.2;
        assertEquals(expectedResult, instance.getPestObservationValue(observationData));
    }
    
    @Test
    public void testGetObservationAtDate() throws Exception
    {
        System.out.println("getObservationAtDate");
        DeliaRadicumFloralisObservationModel instance = new DeliaRadicumFloralisObservationModel();
        ModelConfiguration config = new ModelConfiguration();
        String tzID="Europe/Oslo";
        TimeZone timeZone = TimeZone.getTimeZone(tzID);
        config.setConfigParameter("timeZone", tzID);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        format.setTimeZone(timeZone);

        config.setConfigParameter("startDateGrowth", "2016-04-01");
        config.setConfigParameter("endDateCalculation", "2016-06-01");
        
        List<Observation> pestObservations = new ArrayList<>();
        ObservationImpl obs1 = new ObservationImpl();
        obs1.setTimeOfObservation(format.parse("2016-04-20"));
        obs1.setObservationData("{\"counting01\":3,\"counting02\":2,\"counting03\":3,\"counting04\":4,\"counting05\":0,\"counting06\":0,\"counting07\":0,\"counting08\":0,\"counting09\":0,\"counting10\":0}");
        pestObservations.add(obs1);
        /*ObjectMapper om = new ObjectMapper();
        System.out.println(om.writeValueAsString(obs1));*/
        
        
        ObservationImpl obs2 = new ObservationImpl();
        obs2.setTimeOfObservation(format.parse("2016-04-24"));
        obs2.setObservationData("{\"counting01\":3,\"counting02\":12,\"counting03\":13,\"counting04\":14,\"counting05\":20,\"counting06\":18,\"counting07\":15,\"counting08\":13,\"counting09\":18,\"counting10\":22}");
        pestObservations.add(obs2);
        
        config.setConfigParameter("pestObservations", pestObservations);
        
        instance.setConfiguration(config);
        
        Date testDate = format.parse("2016-04-21");
        Observation obs = instance.getObservationAtDate(testDate);
        assertNull(obs);
        
        testDate = format.parse("2016-04-25");
        obs = instance.getObservationAtDate(testDate);
        assertNull(obs);
        
        testDate = format.parse("2016-04-19");
        obs = instance.getObservationAtDate(testDate);
        assertNull(obs);
        
        testDate = format.parse("2016-04-20");
        obs = instance.getObservationAtDate(testDate);
        assertEquals(testDate,obs.getTimeOfObservation());
    }
    
    @Test
    public void testGetObservationValidForDate() throws Exception
    {
        System.out.println("getObservationValidForDate");
        DeliaRadicumFloralisObservationModel instance = new DeliaRadicumFloralisObservationModel();
        ModelConfiguration config = new ModelConfiguration();
        String tzID="Europe/Oslo";
        TimeZone timeZone = TimeZone.getTimeZone(tzID);
        config.setConfigParameter("timeZone", tzID);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        format.setTimeZone(timeZone);

        config.setConfigParameter("startDateGrowth", "2016-04-01");
        config.setConfigParameter("endDateCalculation", "2016-06-01");
        
        List<Observation> pestObservations = new ArrayList<>();
        ObservationImpl obs1 = new ObservationImpl();
        obs1.setTimeOfObservation(format.parse("2016-04-20"));
        obs1.setObservationData("{\"counting01\":3,\"counting02\":2,\"counting03\":3,\"counting04\":4,\"counting05\":0,\"counting06\":0,\"counting07\":0,\"counting08\":0,\"counting09\":0,\"counting10\":0}");
        pestObservations.add(obs1);
        
        ObservationImpl obs2 = new ObservationImpl();
        obs2.setTimeOfObservation(format.parse("2016-04-24"));
        obs2.setObservationData("{\"counting01\":3,\"counting02\":12,\"counting03\":13,\"counting04\":14,\"counting05\":20,\"counting06\":18,\"counting07\":15,\"counting08\":13,\"counting09\":18,\"counting10\":22}");
        pestObservations.add(obs2);
        
        config.setConfigParameter("pestObservations", pestObservations);
        
        instance.setConfiguration(config);
        
        Date testDate = format.parse("2016-04-21");
        Observation obs = instance.getObservationValidForDate(testDate);
        assertEquals(format.parse("2016-04-20"), obs.getTimeOfObservation());
        
        testDate = format.parse("2016-04-25");
        obs = instance.getObservationValidForDate(testDate);
        assertEquals(format.parse("2016-04-24"), obs.getTimeOfObservation());
        
        testDate = format.parse("2016-04-19");
        obs = instance.getObservationValidForDate(testDate);
        assertNull(obs);
    }
}
