<template>
<div>
    <router-view></router-view>
</div>
</template>

<script>
import Login from './components/Login.vue'
import Signup from './components/Signup.vue'

export default {
  name: 'App',
  components: {
    Login,
    Signup
  }
}
</script>

<style>

</style>
