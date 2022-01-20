<template>
<div>
  <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css" media="screen" rel="stylesheet" type="text/css">
  <div id="login-box">
      <h1>登录用户</h1>
      <div class="form">
          <div class="item">
              <i class="fa fa-user-circle-o" aria-hidden="true"></i>
              <input type="text" placeholder="请输入用户名" v-model="username">
          </div>
          <div class="item">
              <i class="fa fa-key" aria-hidden="true"></i>
              <input type="text" placeholder="请输入密码" v-model="password">
          </div>
    </div>
      <button @click="login" class="click_button" to="/layout">登录</button>
      <router-link class="click_button router_link" to="/login/signup">注册</router-link>
  </div>
</div>
</template>

<script>
export default {
  name: 'Login',
  data: function(){
    return {
        username: "",
        password: "",
        check: false
    }
  },
  methods:{
    login: function() {
      if (this.username == "" || this.password == ""){
        this.check = true
        alert("用户名或密码为空")
        return
      }

      this.$axios.get('/login/api/login', {
        params: {
          username: this.username,
          password: this.password
        }
      }).then(res => {
        if(res.data.code == "2000"){
          sessionStorage.setItem('accountId', res.data.data.body.accountId)
          sessionStorage.setItem('token', res.data.data.token)
          this.$router.push('/layout')
        }else{
          alert("登录失败")
          this.password = ""
          return
        }
      })
    }
  }
}
</script>

<style scoped>
#login-box {
    width: 30%;
    height: auto;
    margin: 0 auto;
    margin-top: 15%;
    text-align: center;
    background: #00000011;
    padding: 20px 50px;
}

#login-box h1{
    color: black;
}
#login-box .form .item input{
    margin-top: 15px;
}
#login-box .form i{
    font-size: 18px;
    color: rgb(14, 13, 13);
}
#login-box .form .item input {
    width: 180px;
    font-size: 18px;
    border: 0;
    border-bottom: 2px solid#fff;
    padding: 5px 10px;
    background:#ffffff00;
    color: rgb(22, 21, 21);
}

.router_link {
  text-decoration: none;
  display: inline-block;
}

.click_button {
    /* margin-top: 15px; */
    margin:15px 10px 0px 10px;
    width: 180px;
    height: 30px;
    font-size: 20px;
    font-weight: 700;
    color: #fff;
    background:linear-gradient(to right,#beb7bb 0%,#131101e1 100%);
    border: 0;
    border-radius: 15px;
    cursor: pointer;
}
.click_button:hover {
    background:linear-gradient(to right,#f3097e 0%,#e6d813e1 100%);
}
</style>
