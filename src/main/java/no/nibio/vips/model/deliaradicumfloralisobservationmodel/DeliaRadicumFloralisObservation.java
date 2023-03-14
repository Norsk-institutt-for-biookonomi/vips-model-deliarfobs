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

/**
 * @copyright 2016 <a href="http://www.nibio.no/">NIBIO</a>
 * @author Tor-Einar Skog <tor-einar.skog@nibio.no>
 */
public class DeliaRadicumFloralisObservation {
    
    private Integer 
            counting01,
            counting02,
            counting03,
            counting04,
            counting05,
            counting06,
            counting07,
            counting08,
            counting09,
            counting10;
    
   

    public DeliaRadicumFloralisObservation(
            
            Integer counting01,
            Integer counting02,
            Integer counting03,
            Integer counting04,
            Integer counting05,
            Integer counting06,
            Integer counting07,
            Integer counting08,
            Integer counting09,
            Integer counting10
    )
    {
        this.counting01 = counting01;
        this.counting02 = counting02;
        this.counting03 = counting03;
        this.counting04 = counting04;
        this.counting05 = counting05;
        this.counting06 = counting06;
        this.counting07 = counting07;
        this.counting08 = counting08;
        this.counting09 = counting09;
        this.counting10 = counting10;
    }
    
    public Double getAverage()
    {
        return
                (this.counting01
                + this.counting02
                + this.counting03
                + this.counting04
                + this.counting05
                + this.counting06
                + this.counting07
                + this.counting08
                + this.counting09
                + this.counting10)
                /10.0;
    }
    
    /**
     * @return the counting01
     */
    public Integer getCounting01() {
        return counting01;
    }

    /**
     * @param counting01 the counting01 to set
     */
    public void setCounting01(Integer counting01) {
        this.counting01 = counting01;
    }

    /**
     * @return the counting02
     */
    public Integer getCounting02() {
        return counting02;
    }

    /**
     * @param counting02 the counting02 to set
     */
    public void setCounting02(Integer counting02) {
        this.counting02 = counting02;
    }

    /**
     * @return the counting03
     */
    public Integer getCounting03() {
        return counting03;
    }

    /**
     * @param counting03 the counting03 to set
     */
    public void setCounting03(Integer counting03) {
        this.counting03 = counting03;
    }

    /**
     * @return the counting04
     */
    public Integer getCounting04() {
        return counting04;
    }

    /**
     * @param counting04 the counting04 to set
     */
    public void setCounting04(Integer counting04) {
        this.counting04 = counting04;
    }

    /**
     * @return the counting05
     */
    public Integer getCounting05() {
        return counting05;
    }

    /**
     * @param counting05 the counting05 to set
     */
    public void setCounting05(Integer counting05) {
        this.counting05 = counting05;
    }

    /**
     * @return the counting06
     */
    public Integer getCounting06() {
        return counting06;
    }

    /**
     * @param counting06 the counting06 to set
     */
    public void setCounting06(Integer counting06) {
        this.counting06 = counting06;
    }

    /**
     * @return the counting07
     */
    public Integer getCounting07() {
        return counting07;
    }

    /**
     * @param counting07 the counting07 to set
     */
    public void setCounting07(Integer counting07) {
        this.counting07 = counting07;
    }

    /**
     * @return the counting08
     */
    public Integer getCounting08() {
        return counting08;
    }

    /**
     * @param counting08 the counting08 to set
     */
    public void setCounting08(Integer counting08) {
        this.counting08 = counting08;
    }

    /**
     * @return the counting09
     */
    public Integer getCounting09() {
        return counting09;
    }

    /**
     * @param counting09 the counting09 to set
     */
    public void setCounting09(Integer counting09) {
        this.counting09 = counting09;
    }

    /**
     * @return the counting10
     */
    public Integer getCounting10() {
        return counting10;
    }

    /**
     * @param counting10 the counting10 to set
     */
    public void setCounting10(Integer counting10) {
        this.counting10 = counting10;
    }
    
    
}
