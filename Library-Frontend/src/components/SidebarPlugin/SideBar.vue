<template>
  <nav
    class="navbar navbar-vertical fixed-left navbar-expand-md navbar-light bg-white"
    id="sidenav-main"
  >
    <div class="container-fluid">
      <!--Toggler-->
      <navbar-toggle-button @click="showSidebar">
        <span class="navbar-toggler-icon"></span>
      </navbar-toggle-button>
      <router-link class="navbar-brand" to="/about">
        <img :src="logo" class="navbar-brand-img" alt="..." />
      </router-link>

      <slot name="mobile-right">
        <ul class="nav align-items-center d-md-none">
          <base-dropdown class="nav-item" position="right">
            <template v-slot:title>
              <a class="nav-link" role="button">
                <div class="media align-items-center">
                  <span class="avatar avatar-sm rounded-circle">
                    <img alt="Image placeholder" src="img/theme/cat.jpg" />
                  </span>
                </div>
              </a>
            </template>

            <div class="dropdown-header noti-title">
              <h6 class="text-overflow m-0">Welcome, {{ model.username }}!</h6>
            </div>
            <router-link to="/profile" class="dropdown-item">
              <i class="ni ni-single-02"></i>
              <span>My profile</span>
            </router-link>
            <div class="dropdown-divider"></div>
            <a href="/#/login" class="dropdown-item">
              <i class="ni ni-user-run"></i>
              <span>Logout</span>
            </a>
          </base-dropdown>
        </ul>
      </slot>
      <slot></slot>
      <div
        v-show="this.$sidebar.showSidebar"
        class="navbar-collapse collapse show"
        id="sidenav-collapse-main"
      >
        <div class="navbar-collapse-header d-md-none">
          <div class="row">
            <div class="col-6 collapse-brand">
              <router-link to="/">
                <img :src="logo" />
              </router-link>
            </div>
            <div class="col-6 collapse-close">
              <navbar-toggle-button
                @click="closeSidebar"
              ></navbar-toggle-button>
            </div>
          </div>
        </div>

        <ul class="navbar-nav">
          <slot name="links"> </slot>
        </ul>
      </div>
    </div>
  </nav>
</template>
<script>
import NavbarToggleButton from "@/components/NavbarToggleButton";

export default {
  name: "sidebar",
  components: {
    NavbarToggleButton,
  },
  props: {
    logo: {
      type: String,
      default: "img/brand/logo.png",
      description: "Sidebar app logo",
    },
    autoClose: {
      type: Boolean,
      default: true,
      description:
        "Whether sidebar should autoclose on mobile when clicking an item",
    },
  },
  data() {
    return {
      model: {
        username: window.localStorage.getObject("user").username,
      },
    }
  },
  provide() {
    return {
      autoClose: this.autoClose,
    };
  },
  methods: {
    closeSidebar() {
      this.$sidebar.displaySidebar(false);
    },
    showSidebar() {
      this.$sidebar.displaySidebar(true);
    },
  },
  beforeUnmount() {
    if (this.$sidebar.showSidebar) {
      this.$sidebar.showSidebar = false;
    }
  },
};
</script>
