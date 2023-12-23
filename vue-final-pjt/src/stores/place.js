import { ref, computed, } from 'vue'
import { defineStore } from 'pinia'
import axios from 'axios';
export const usePlaceStore = defineStore('place', () => {

    const reviewPlaceId = ref(-1);
    const places = ref([]);
    const searchPlaces =ref([]);
    const limitStores = ref([]);
    const fitSearchTF = ref(false);
    const fitSearchName = ref("");
    const getPlaces = async () => {
        try {
            const URL = import.meta.env.VITE_APP_API_PLACE_URL;
            const response = await axios.get(URL);
            places.value = response.data;
        } catch (error) {
            console.log(error);
        }
    };

    const setReviewPlaceId = (placeId) => {
        reviewPlaceId.value = placeId;
    }



    return { places, limitStores, searchPlaces, fitSearchTF, fitSearchName, getPlaces, reviewPlaceId, setReviewPlaceId}
})
