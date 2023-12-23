<template>
    <div class="container">
        <h3 class="mb-4">🌳 {{ place.addressGu + " " + place.addressDong + " " + place.공원명 }}</h3>
        <div class="mapContainer">
            <div id="map2"></div>
            <dl class=" mx-5 px-2 py-2">
                <dt>무료헬스장</dt>
                <dd> {{ place.addressGu + " " + place.addressDong + " " + place.공원명 }}</dd>

                <dt>설치주소</dt>
                <dd>{{ place.설치주소 }}</dd>
            
                <dt>설치 운동기구 종류</dt>
                <dd>{{ place.설치운동기구종류 }}</dd>
            
                <dt>연간 운동기구 관리 횟수</dt>
                <dd>{{ place.연간운동기구관리횟수 }}</dd>

                <dt>고장난 운동기구명</dt>
                <dd>{{ place.고장난운동기구명 }}</dd>

                <dt>담당부서명</dt>
                <dd>{{ place.담당부서명 }}</dd>

                <dt>담당부서 전화번호</dt>
                <dd>{{ place.담당부서전화번호 }}</dd>

                <TheReviewList :placeId= "placeId" key="reviewList"/>
                <button type="submit" class="btn btn-primary mb-3" @click.stop.prevent=reviewAdd
                    >리뷰작성</button>
            </dl>
        </div>  
    </div>
    
</template>

<script setup>
import axios from 'axios';
import { ref, onMounted, toRaw, watch } from 'vue';
import { useRoute, useRouter } from 'vue-router';
import TheReviewList from '@/components/review/TheReviewList.vue';
import { usePlaceStore } from '../stores/place';
import { useUserStore } from '../stores/user';
const route = useRoute();
const router = useRouter();
const place = ref({});
const placeId = ref(route.params.placeId);
const placeStore = usePlaceStore();
const userStore = useUserStore();
placeStore.setReviewPlaceId(placeId.value); 

const reviewAdd = () => {
    if (userStore.loginTF === false) {
        alert("리뷰 작성을 하려면 로그인을 해주세요")
        return
    }
    else {
        router.push(`/placeDetail/${placeId.value}/reviewAdd`)
    }

}

watch(() => route.params.placeId, async(newParam, oldParam) => {
    placeId.value = newParam;
    let URL = import.meta.env.VITE_APP_API_PLACE_URL + "/" + placeId.value;
    const response = await axios.get(URL);
    place.value = response.data;

    initMap();
})


let map = null;
const initMap = function () {
    let myCenter = new kakao.maps.LatLng(place.value.위도, place.value.경도); //공원위치


    const container = document.getElementById('map2');
    const options = {
        center: myCenter,
        level: 3,
    }; // 지도 객체를 등록합니다.
    map = new kakao.maps.Map(container, options);
    new kakao.maps.Marker({
        map,
        position: myCenter,
    });
    // 일반 지도와 스카이뷰로 지도 타입을 전환할 수 있는 지도타입 컨트롤을 생성합니다
    const mapTypeControl = new kakao.maps.MapTypeControl();

    // 지도에 컨트롤을 추가해야 지도위에 표시됩니다
    map.addControl(mapTypeControl, kakao.maps.ControlPosition.TOPRIGHT);

    // 지도 확대 축소를 제어할 수 있는  줌 컨트롤을 생성합니다
    const zoomControl = new kakao.maps.ZoomControl();
    map.addControl(zoomControl, kakao.maps.ControlPosition.RIGHT);

};


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

onMounted(async () => {
    try {
        let URL = import.meta.env.VITE_APP_API_PLACE_URL + "/" + placeId.value;
        console.log(URL);
        const response = await axios.get(URL);
        place.value = response.data;
        
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


    } catch (error) {
        console.log(error);
    };

})



</script>

<style  scoped>
#map2 {
    width: 40%;
    height: 50vh;
    z-index: 0;
}

dt{
    color: #1A5E21;
    font-size: medium;
}

h3{
  font-family: 'Pretendard-Regular';
  font-weight: 600;
}
.container {
    height: auto;
  min-height: 80vh;
  font-family: 'Pretendard-Regular';
}
.mapContainer {
    display: flex;
    /* font-family: 'Pretendard-Regular'; */
}
</style>