<template>
  <div>
    <div id="wrapper">
      <header>
        <TheHeaderNav />
      </header>
        <div v-if="$route.path === '/'">
                <TheMastHead/>
        </div>
      <main class="row">
        <div class="row">

          <div class="col">
            
          </div>
          <div class="row rowSection col-9">
            <section class="col-9" id="rootSection">
              
              <RouterView />
            </section>
            <aside v-show="$route.path !== '/kind' && $route.path !== '/about'" class="col-3 ps-3 pe-3">
              <div v-if="$route.path !== '/signup'">
                <div v-if="store.loginTF === false">
                  <LoginForm />
                </div>
                <div v-else>
                  <SigninForm />
                </div>
                <TheFavList />
                
              </div>
              
            </aside>
          </div>
          <div class="col">
            
          </div>
        </div>
      </main>

    </div>
    <footer>
      <TheFooter />
    </footer>
  </div>
</template>


<script setup>
import TheHeaderNav from "./components/common/TheHeaderNav.vue";
import TheFooter from "./components/common/TheFooter.vue";
import LoginForm from "./components/common/TheLoginForm.vue";
import TheFavList from "./components/common/TheFavList.vue";
import { useUserStore } from './stores/user'
import SigninForm from "./components/common/TheSigninForm.vue";
import { onMounted, ref, } from "vue";
import TheMastHead from "./components/common/TheMastHead.vue";


const store = useUserStore();


onMounted(() => {
  if (sessionStorage.getItem("user") !== null) {
    store.loginTF = true;
  }
})



</script>

<style scoped>
@font-face {
  font-family: 'EASTARJET-Medium';
  src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_231029@1.1/EASTARJET-Medium.woff2') format('woff2');
  font-weight: 500;
  font-style: normal;
}

@font-face {
  font-family: 'JalnanGothic';
  src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_231029@1.1/JalnanGothic.woff') format('woff');
  font-weight: normal;
  font-style: normal;
}

@font-face {
  font-family: 'Pretendard-Regular';
  src: url('https://cdn.jsdelivr.net/gh/Project-Noonnu/noonfonts_2107@1.1/Pretendard-Regular.woff') format('woff');
  font-weight: 400;
  font-style: normal;
}

.container-fluid{
  margin-top: 100px;
}

#wrapper {
  height: auto;
  min-height: 100%;
  padding-bottom: 0;
}
header {
  width: 100%;
}
.row {
  --bs-gutter-x: 0 !important
}
.rowSection {
  margin-top: 20vh;
}
footer {
  height: 0;
  position: relative;
  transform: translateY(-100%);
}
</style>