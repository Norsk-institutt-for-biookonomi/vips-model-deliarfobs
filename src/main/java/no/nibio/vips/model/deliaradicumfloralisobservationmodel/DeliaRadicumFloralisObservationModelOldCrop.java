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
