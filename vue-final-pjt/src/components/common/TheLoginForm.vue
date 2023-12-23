<template>
    <ul class="nav nav-tabs" style="width: 18rem;">
        <li class="nav-item">
            <p class="nav-link" :class="{ 'active' : signUpTF === false}" @click="loginClick">Login</p>
        </li>
        <li class="nav-item">
            <p class="nav-link" :class="{ 'active' : signUpTF === true}" @click="signUpClick">SignUp</p>
        </li>

    </ul>
    <div v-if="signUpTF === false">
        <form class="col" style="width: 18rem;">
            <div class="row-auto">
                <label for="inputId1">Email</label>
                <input type="text" v-model="inputId1" class="form-control" id="staticId1" placeholder="id">
            </div>
            <div class="row-auto">
                <label for="inputPassword1">Password</label>
                <input type="password" v-model="inputPassword1" class="form-control" id="inputPassword1"
                    placeholder="Password">
            </div>

            <div class="d-flex justify-content-end">
                <button type="submit" class="btn btn-primary mb-3" @click.stop.prevent=signIn
                    @keyup.enter.stop.prevent=signIn>로그인</button>
            </div>

        </form>
    </div>
    <div v-else>
        <form class="col" style="width: 18rem;">
            <div class="row-auto">
                <label for="name">Name</label>
                <input type="text" v-model="name" class="form-control" id="name" placeholder="Name" />
            </div>
            <div class="row-auto">
                <label for="email">Email</label>
                <input type="email" v-model="email" class="form-control" id="email" placeholder="Email" />
            </div>
            <div class="row-auto">
                <label for="pw">Password</label>
                <input type="password" v-model="pw" class="form-control" id="pw" placeholder="Password" />
            </div>
            <div class="d-flex justify-content-end">
                <button type="submit" class="btn btn-primary mb-3" @click.stop.prevent=signupId
                    @keyup.enter.stop.prevent=signupId>회원가입</button>
            </div>
        </form>
    </div>
</template>

<script setup>
import { ref } from 'vue'
import { useUserStore } from '@/stores/user';
import axios from 'axios';
import router from "@/router";
const inputId1 = ref("");
const inputPassword1 = ref("");
const store = useUserStore();
const signUpTF = ref(false);
const name = ref("");
const email = ref("");
const pw = ref("");
const signUpClick = () => {
    signUpTF.value = true;
}
const loginClick = () => {
    signUpTF.value = false;
}




const signIn = async () => {
    const URL = import.meta.env.VITE_APP_API_URL + "/" + "login";

    axios.
        post(URL, {
            email: inputId1.value,
            password: inputPassword1.value,
        }).then((res) => {
            sessionStorage.setItem("user", JSON.stringify(res.data));
            store.loginTF = true;
            alert("로그인 되었습니다!")
            router.replace("/");
        }).catch((error) => {

            alert(error.response.data.message);
        })


}

const signupId = async () => {
    if (name.value === "" || email.value === "" || pw.value === "" ) {
        alert("값을 입력해주세요");
        return;
    }




    const URL = import.meta.env.VITE_APP_API_URL + "/" + "signup";
    axios
        .post(URL, {
            name: name.value,
            email: email.value,
            password: pw.value,
        })
        .then((res) => {

            console.log(res)
            alert("회원가입 완료!, 로그인 해주세요");
            name.value="";
            pw.value="";
            email.value="";
            signUpTF.value = false;
        })
        .catch((res) => {
            alert("중복 아이디입니다. 다른 아이디를 사용해주세요")
            console.log(res);
        })
    



}

</script>

<style scoped>
.btn {
    margin-top: 5px;
}

.nav-item:hover {
    cursor : pointer;
}
a {
    text-decoration-line: none;
    color: green;
}
</style>