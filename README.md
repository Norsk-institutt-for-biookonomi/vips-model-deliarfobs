# DeliaRadicumFloralisObservationModel

<p>The warning system model is based on weekly observations of oviposition by the brassica root flies [CITATION Bli991 \l 1044 ]. The model is based in its entirety on observations, with no input of weather data or weather forecasts. The model does not distinguish between the cabbage maggot and the turnip fly. The observations consist of collecting sand from the base of 10 plants and floating the eggs in water. The counts are registered in VIPS and the mean number of eggs is calculated. The observations are compared to damage thresholds [CITATION Bli99 \l 1044 ] and warnings are calculated.</p>
<p>The damage thresholds are related to the plants developmental stage and tell how many eggs that can be on a plant before there will be a reduction in growth and yield. VIPS presents two warning system models based on damage thresholds: one for newly planted cabbage and one for cabbage that has been in the field more than 4 weeks. The model can also be set up as a private warning for the farmer&rsquo;s own field, in which case the model will vary between the two different damage thresholds based on the age of the cabbage crop (starting at the time of planting).</p>
<p>The warning system model is only valid for cabbage, cauliflower and broccoli. The damage threshold for cabbage, cauliflower and broccoli the first 4 weeks after planting is 14 eggs per plant per week. After 4 weeks the damage threshold changes to 40 eggs per plant per week. Damage thresholds have not been determined for other crucifers.</p>
<p>The model is adapted for VIPS by Dr. Richard Meadow.</p>
<h3>Interpreting the warning</h3>
<p>Green rectangles indicate that the damage threshold for cabbage based on the time from planting until the registration has not been reached and control measures are not needed.</p>
<p>Red rectangles indicate that the damage threshold f for cabbage based on the time from planting until the registration has been reached and it may be necessary to use control measures. The field should be checked to evaluate whether treatment is necessary.</p>
<p>This model does not use yellow rectangles. The warning goes directly from green to red when the damage threshold is exceeded.</p>
<p>Blue rectangles indicate that there are missing observations. An observation is valid for 8 days. If there are more than 8 days before a new observation is registered in the system, the model will give a blue warning.</p>
<p>The damage thresholds are related to the plants developmental stage and tell how many eggs that can be on a plant before there will be a reduction in growth and yield.</p>
<p>The warning should tell the best time for treatment in relation to the damage thresholds. If the number of eggs registered exceeds the damage threshold, treatment against the maggots is recommended, if the plants are not protected by other means. Observe the &rdquo;days-to-harvest&rdquo;. If the seeds (cauliflower or broccoli) were treated against maggots, treatment early in the season is not necessary.</p>
<h3>The warning season . start and end of the warning</h3>
<p>Starting point: Registration of eggs and warning begins at planting/sprouting in the field.</p>
<p>Ending: The registrations continue until control of the root flies is no longer necessary.</p>
<h3>Testing and validating the model</h3>
<h4>Nationally</h4>
<p>The damage thresholds that are the basis of the model are used in Norwegian cabbage growing. The model has not been validated.</p>
<h4>Internationally</h4>
<p>The damage thresholds that are the basis of the model are based on research in Denmark and Norway. The extent of their current use in Denmark is not known.</p>
<h3>References</h3>
<p>Bligaard, J. (1999). Damage Thresholds for Cabbage Root Fly [Delia radicum (L.)] in Cauliflower. Assessed from Pot Experiments. <em>Acta Agriculturae Scandinavica, Section B - Soil &amp; Plant Science , 49</em>(1), pp. 57-64. doi:10.1080/09064719950135713</p>
<p>Bligaard, J., Meadow, R. , Nielsen, O., &amp; Percy-Smith, A. (1999). Evaluation of felt traps to estimate egg numbers of cabbage root fly, Delia radicum, and turnip root fly, Delia floralis in commercial crops. <em>Entomologia Experimentalis et Applicata, 90</em>, pp. 141&ndash;148. doi:10.1046/j.1570-7458.1999.00432.x</p>
<p>Contacts Annette F. Schj&oslash;ll <a href="mailto:annette.folkedal.schjoll@nibio.no">annette.folkedal.schjoll@nibio.no</a><br /> Tor J. Johansen <a href="mailto:tor.johansen@nibio.no">tor.johansen@nibio.no</a></p>
<h3>Links for more information (Norwegian only)</h3>
<p>Information about registration of oviposition by cabbage maggots and turnip flies: <a href="http://www.vips-landbruk.no/media/attachments/information/RegEggKalflue2016.pdf">http://www.vips-landbruk.no/media/attachments/information/RegEggKalflue2016.pdf</a></p>
<p>Plantevernleksikonet:&nbsp;<a href="https://www.plantevernleksikonet.no/l/oppslag/338/" target="_blank">https://www.plantevernleksikonet.no/l/oppslag/338/</a></p>

### Development

Clone Gitlab repository

```
$ git clone https://gitlab.nibio.no/VIPS/models/java/Model_DELIARFOBS.git
$ cd Model_DELIARFOBS
```
