<template>
  <div>
    <div id="grid-container">
      <!-- <div id="intro">
       
          Drop a pin one of 3 ways:
          <ol>
           <li>Enter a latitude, longitude, and description, press the Add Marker button</li> 
           <li>Right click on the map, and enter a description when prompted</li> 
           <li> have a service populate the locations array.</li>
          </ol>
      </div> -->

      <div id="map"></div>

      <!-- <div id="latitude" class ='coord-input'>
        <span>Latitude:</span><br />
        <input id="latitude-input" type="text" /><br />
        <select id="latitude-direction">
          <option>North</option>
          <option>South</option></select
        ><br />
      </div> -->

      <!-- <div id="longitude" class ='coord-input'>
        <span>Longitude:</span><br />
        <input id="longitude-input" type="text" /><br />
        <select id="longitude-direction">
          <option>West</option>
          <option>East</option>
        </select>
        <br />
      </div> -->

      <!-- <div id="description" class ='coord-input'>
        <span class="menu-label">Description</span><br />
        <input id="description-input" type="text" /><br />
      </div> -->

      <!-- <div id="buttons" class ='coord-input'> 
        <input v-on:click="addPinViaInput" type="button" value="Add Marker" /> <br><br>
        <input v-on:click="summarizeMarkers" type="button" value="Summarize All Markers"/>
      </div> -->
<!-- 
      <div id="report"></div> -->
    </div>
  </div>
</template>

<script>
export default {
  name: "Map",
  data() {
    return {
      map: null,
      mapCenter: { lat: 42.3327, lng: -83.0458 },
      locations: [
        {
          coord: { lat: 41.6206368854895, lng: -87.82711344347209 },
          name: "Silver Lakes",
        },
        {
          coord: { lat: 41.65898820778067, lng: -87.94645160774927 },
          name: "Glen Eagles",
        },
        {
          coord: { lat: 41.5463249833147, lng: -87.69856841298956 },
          name: "Coyote Run",
        },
        {
          coord: { lat: 41.77047253571767, lng: -88.07419282198339 },
          name: "Seven Bridges",
        },
        {
          coord: { lat: 41.59725040620492, lng: -87.76417308148898 },
          name: "George W. Dunn National",
        },
        {
          coord: { lat: 41.57659845907173, lng: -88.01467231235299 },
          name: "Broken Arrow",
        },
        {
          coord: { lat: 41.68118548977973, lng: -87.80092734997494 },
          name: "Waters Edge",
        },
        {
          coord: { lat: 41.92609208391545, lng: -88.20721343076369 },
          name: "St. Andrews",
        },
        {
          coord: { lat: 41.81862766865637, lng: -87.97136557460996 },
          name: "Willow Crest",
        },
        {
          coord: { lat: 41.97229238080383, lng: -88.04807281480507 },
          name: "Medinah C.C.",
        },
        {
          coord: { lat: 36.712896392981634, lng: -76.28734397139226 },
          name: "Chesapeake",
        },
        {
          coord: { lat: 36.73389415162249, lng: -76.03115608673812 },
          name: "Heron Ridge",
        },
        {
          coord: { lat: 36.74290750250611, lng: -75.98297584812373 },
          name: "Hells Point",
        },
        {
          coord: { lat: 36.77738294483917, lng: -76.12829223277625 },
          name: "HoneyBee",
        },
        {
          coord: { lat: 36.80201235202165, lng: -75.98531476718111 },
          name: "Red Wing Lake",
        },
        {
          coord: { lat: 36.80322137817217, lng: -76.33906152906286 },
          name: "Bide-A-Wee",
        },
        {
          coord: { lat: 36.77310681931312, lng: -76.15975353277635 },
          name: "Stumpy Lake",
        },
        {
          coord: { lat: 36.84403941360115, lng: -76.51732000022511 },
          name: "Sleepy Hole",
        },
        {
          coord: { lat: 36.92213706456485, lng: -76.28729230207921 },
          name: "Sewells Point",
        },
        {
          coord: { lat: 36.876723638377364, lng: -76.16398605975375 },
          name: "Cypress Point",
        },
        {
          coord: { lat: 42.81141040357831, lng: -84.88496090375465 },
          name: "Willow Wood",
        },
        {
          coord: { lat: 42.42726831446238, lng: -83.39822614423757 },
          name: "Fox Creek",
        },
        {
          coord: { lat: 42.51459591974049, lng: -83.35267778841788 },
          name: "Glen Oaks",
        },
        {
          coord: { lat: 42.498601499096345, lng: -83.42416785958173 },
          name: "Copper Creek",
        },
        {
          coord: { lat: 42.531436977693325, lng: -83.24762465958071 },
          name: "Lincoln Hills",
        },
        {
          coord: { lat: 42.507403300046924, lng: -83.14092034980393 },
          name: "Red Run",
        },
        {
          coord: { lat: 42.47379662129029, lng: -83.56676615958256 },
          name: "Walnut Creek",
        },
        {
          coord: { lat: 42.585971967924024, lng: -83.38289280747458 },
          name: "Orchard Lake",
        },
        {
          coord: { lat: 42.589480856513966, lng: -83.24039637306899 },
          name: "Stonycroft Hills",
        },
        {
          coord: { lat: 42.43787650301808, lng: -83.44670908656401 },
          name: "Meadowbrook",
        },
        //For the Lat/Long, North && East are always Positive, West && South are Negative on Grid//
      ],
    };
  },

   methods: {
    initMap() {
      this.calculateCenter();
      this.map = new window.google.maps.Map(document.getElementById("map"), {
        center: this.mapCenter,
        zoom: 5,
        maxZoom: 20,
        minZoom: 3,
        streetViewControl: true,
        mapTypeControl: true,
        fullscreenControl: true,
        zoomControl: true,
      });
      let noPOIStyle = [
        {
          featureType: "poi",
          elementType: "labels",
          stylers: [{ visibility: "off" }],
        },
      ];
      this.map.setOptions({ styles: noPOIStyle });
      window.google.maps.event.addListener(this.map, "rightclick", (event) => {
        this.addPinViaClick(event);
      });
    },
    calculateCenter() {
      
      let latTotal =0, lngTotal = 0;
      for (let i=0; i < this.locations.length; i++) {
          latTotal +=  parseFloat(this.locations[i].coord.lat);
          lngTotal +=  parseFloat(this.locations[i].coord.lng);
      }
      const lat = latTotal / this.locations.length;
      const lng = lngTotal / this.locations.length;
      this.mapCenter = {lat: lat, lng : lng};
    },
    makeMarkerObj(latLng, name) {
      const markerObj = { coord: latLng, name: name };
      return markerObj;
    },
    addPinViaClick(event) {
      let description = window.prompt("Enter a description");
      const markerObj = this.makeMarkerObj(event.latLng.toJSON(), description);
      this.locations.push(markerObj);
      this.dropPin(markerObj);
    },
    addPinViaInput() {
      let latitudeValue = parseFloat(document.getElementById("latitude-input").value
      );
      let longitudeValue = parseFloat(document.getElementById("longitude-input").value
      );
      const latSelectIndex = document.getElementById("latitude-direction").selectedIndex;
      const lonSelectIndex = document.getElementById("longitude-direction").selectedIndex;
      const latitudeDirection = document.getElementById("latitude-direction").options[latSelectIndex].text;
      const longitudeDirection = document.getElementById("longitude-direction").options[lonSelectIndex].text;
      const description = document.getElementById("description-input").value;
      latitudeValue = latitudeDirection === "North" ? latitudeValue : -1 * latitudeValue;
      longitudeValue = longitudeDirection === "East" ? longitudeValue : -1 * longitudeValue;
      const markerObj = this.makeMarkerObj(
        { lat: latitudeValue, lng: longitudeValue },
        description
      );
      this.locations.push(markerObj);
      this.dropPin(markerObj);
    },
    dropPins() {
      this.locations.forEach((x) => this.dropPin(x));
    },
    dropPin(markerObj) {
      new window.google.maps.Marker({
        position: markerObj.coord,
        map: this.map,
        label: {
          text: markerObj.name,
          color: "blue",
        },
      });
    },
    summarizeMarkers() {
      const dataSection = document.getElementById("report");
      let text = "<ol>";
      this.locations.forEach(
        (x) =>
          (text =
            text +
            `<li>Latitude: ${x.coord.lat} Longitude: ${x.coord.lng} Description: ${x.name}` +
            "</li>")
      );
      text += "</ol>";
      dataSection.innerHTML = text;
    },
  },
  mounted() {
    this.initMap();
    this.dropPins();
  },
};
</script>

<style>
/* #grid-container {
  display: grid;
  grid-template-columns: 1fr 1fr 1fr 1fr;
  grid-template-areas:
    "intro intro intro intro"
    "latitude longitude description buttons"
    "map map report report";
} */
/* #intro {
    grid-area: intro;
}
#latitude {
  grid-area: latitude;
}
#longitude {
  grid-area: longitude;
}
#description {
  grid-area: description;
}
#buttons {
  grid-area: buttons;
} */
#map {
  /* grid-area: map; */
  width: 500px;
  height: 400px;
  padding: 25px;
  margin-left: 25%;
}
/* #report {
  grid-area:report;
  width: 500px;
  height: 400px;
  padding: 25px;
  margin: 25px;
  border: 1px dashed gray;
} */
.coord-input {
  border: 1px dashed gray;
  padding: 25px;
  margin: 25px;
}
.input-cell {
  vertical-align: top;
}
</style>