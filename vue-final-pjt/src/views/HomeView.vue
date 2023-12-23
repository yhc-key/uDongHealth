<template>
  <div class="container">
    <h3 class="mb-4">현재 위치에서 가까운 추천 무료 헬스장</h3>
    <div class="mapContainer">
      <div id="map"></div>
      <div>
        <TheLecList />
      </div>

    </div>
  </div>
</template>

<script setup>
import { onMounted, ref, toRaw } from 'vue';
import TheLecList from '../components/common/TheLecList.vue';
import router from '../router';
import axios from 'axios'

const lecList = ref([]);


let map = null;
const initMap = function () {
  let myCenter = new kakao.maps.LatLng(37.501286, 127.0396029); // 역삼역 멀티캠퍼스
  if (navigator.geolocation) {
    navigator.geolocation.getCurrentPosition((position) => {
      const lat = position.coords.latitude;
      const lon = position.coords.longitude;
      myCenter = new kakao.maps.LatLng(lat, lon);
      new kakao.maps.Marker({
        map,
        position: myCenter,
      });
      map.setCenter(myCenter);
    });
  }

  const container = document.getElementById('map');
  const options = {
    center: myCenter,
    level: 3,
  }; // 지도 객체를 등록합니다.
  map = new kakao.maps.Map(container, options);
  new kakao.maps.Marker({
    map,
    position: myCenter,
  });
  lecList.value.forEach((lec) => {
    let otherPosition = new kakao.maps.LatLng(lec.위도, lec.경도);
      new kakao.maps.Marker({
      map,
      position: otherPosition,
    });
    

  })
  // const otherPosition = new kakao.maps.LatLng(37.500286, 127.0394029);
  // new kakao.maps.Marker({
  //   map,
  //   position: otherPosition,
  // }); //마커 넣는 방식


  // 일반 지도와 스카이뷰로 지도 타입을 전환할 수 있는 지도타입 컨트롤을 생성합니다
  const mapTypeControl = new kakao.maps.MapTypeControl();

  // 지도에 컨트롤을 추가해야 지도위에 표시됩니다
  map.addControl(mapTypeControl, kakao.maps.ControlPosition.TOPRIGHT);

  // 지도 확대 축소를 제어할 수 있는  줌 컨트롤을 생성합니다
  const zoomControl = new kakao.maps.ZoomControl();
  map.addControl(zoomControl, kakao.maps.ControlPosition.RIGHT);
};

onMounted(() => {
  if (window.kakao && window.kakao.maps) {
    initMap();
  } else {
    const script = document.createElement('script'); // autoload=false 스크립트를 동적으로 로드하기 위해서 사용
    script.src = `//dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey=${import.meta.env.VITE_APP_MAP_API_KEY
      }`;
    script.addEventListener('load', () => {
      kakao.maps.load(initMap);
    }); //헤드태그에 추가
    document.head.appendChild(script);
  }
  const lat = 37.501286;
  const lon = 127.0396029;
  const URL = import.meta.env.VITE_APP_API_PLACE_URL + "/find/" + lat + "/" + lon;

  axios.get(URL).then((res) =>
    lecList.value = res.data).catch((error) => console.log(error));

  
}); 

const myMarkerPosition = ref([
  [37.501286, 127.0396029],
])

const markers = ref([]);

const displayMarker = function (markerPositions) {
  //마커지우기
  if (markers.value.length > 0) {
    markers.value.forEach((marker) => marker.setMap(null));
  }

  const positions = markerPositions.map(
    (position) => new kakao.maps.LatLng(...position)
  );
  if (positions.length > 0) {
    markers.value = positions.map(
      (position) =>
        new kakao.maps.Marker({
          map: toRaw(map),
          position,
        })
    );

    const bounds = positions.reduce(
      (bounds, latlng) => bounds.extend(latlng),
      new kakao.maps.LatLngBounds()
    );

    toRaw(map).setBounds(bounds);
  }
};
</script>

<style scoped>
h3{
  font-family: 'Pretendard-Regular';
  font-weight: 600;
}

#map {
  z-index: 0;
  width: 40%;
  height: 50vh;
}

.container {
  height: 80vh;
}
.mapContainer {
  display: flex;
}
</style>