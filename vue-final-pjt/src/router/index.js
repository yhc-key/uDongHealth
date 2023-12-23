import { createRouter, createWebHistory } from 'vue-router'
import HomeView from '../views/HomeView.vue'
import SearchView from '../views/SearchView.vue'
import KindView from '../views/KindView.vue'
import PlaceDetailView from '../views/PlaceDetailView.vue'
import ReviewEditView from '@/components/review/ReviewEditView.vue'
import TheSignupView from '../views/TheSignupView.vue'
import ReviewAddView from '@/components/review/ReviewAddView.vue'
import SearchPlace from '@/components/search/SearchPlace.vue'
import SearchFit from '@/components/search/SearchFit.vue'
const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'home',
      component: HomeView
    },
    {
      path: '/about',
      name: 'about',
      // route level code-splitting
      // this generates a separate chunk (About.[hash].js) for this route
      // which is lazy-loaded when the route is visited.
      component: () => import('../views/AboutView.vue')
    },
    {
      path: '/search',
      redirect: '/search/place',
      name: 'search',
      component: SearchView,
      children: [{
        path: '/search/place',
        component: SearchPlace,
      }, {
        path: '/search/fit',
        component: SearchFit
      }]
    },
    {
      path: '/placeDetail/:placeId',
      name: 'placeDetail',
      component: PlaceDetailView
    }, {
      path: '/placeDetail/:placeId/reviewAdd',
      name: 'reviewAdd',
      component : ReviewAddView
    },
    {
      path: '/kind',
      name: 'kind',
      component: KindView,

    }, {
      path: '/reviewEdit/:reviewId/:content',
      name: 'reviewEdit',
      component: ReviewEditView,
    },
    {
      path: '/signup',
      name: 'signup',
      component: TheSignupView
    }
  ]
})

export default router
