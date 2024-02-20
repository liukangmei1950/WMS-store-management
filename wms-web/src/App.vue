<template>
  <div id="app">
<!--    <nav>
      <router-link to="/">Home</router-link> |
      <router-link to="/about">About</router-link>
    </nav>
    <router-view/>-->

<!--<index></index>-->
    <router-view/>
  </div>
</template>

<style>
#app {

}

nav {
  padding: 30px;
}

nav a {
  font-weight: bold;
  color: #2c3e50;
}

nav a.router-link-exact-active {
  color: #42b983;
}
</style>
<script>
import Index from "@/components";
export default {
  components: {

  }
}
</script>