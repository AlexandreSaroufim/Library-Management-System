<template>
  <base-nav
    class="navbar-top navbar-dark"
    id="navbar-main"
    :show-toggle-button="false"
    expand
  >
    <form
      class="navbar-search navbar-search-dark form-inline mr-3 d-none d-md-flex ml-lg-auto"
    >
      <div class="form-group mb-0">
        <base-input
          placeholder="Search"
          class="input-group-alternative"
          alternative=""
          addon-right-icon="fas fa-search"
        >
        </base-input>
      </div>
    </form>
    <ul class="navbar-nav align-items-center d-none d-md-flex">
      <li class="nav-item dropdown">
        <base-dropdown class="nav-link pr-0" position="right">
          <template v-slot:title>
            <div class="media align-items-center">
              <span class="avatar avatar-sm rounded-circle">
                <img alt="User Image" src="img/theme/cat.jpg" />
              </span>
              <div class="media-body ml-2 d-none d-lg-block">
                <span class="mb-0 text-sm font-weight-bold">
                  {{ model.username }}
                </span>
              </div>
            </div>
          </template>
          <div class="dropdown-header noti-title">
            <h6 class="text-overflow m-0">Welcome, {{ model.username }}!</h6>
          </div>
          <router-link to="/profile" class="dropdown-item">
            <i class="ni ni-single-02"></i>
            <span>My profile</span>
          </router-link>
          <div class="dropdown-divider"></div>
          <router-link to="/#/login" class="dropdown-item">
            <i class="ni ni-user-run"></i>
            <span>Logout</span>
          </router-link>
        </base-dropdown>
      </li>
    </ul>
  </base-nav>
</template>
<script>
export default {
  name: "navbar",
  data() {
    return {
      activeNotifications: false,
      showMenu: false,
      searchQuery: "",
      model: {
        username: window.localStorage.getObject("user").username,
      },
    };
  },
  methods: {
    toggleSidebar() {
      this.$sidebar.displaySidebar(!this.$sidebar.showSidebar);
    },
    hideSidebar() {
      this.$sidebar.displaySidebar(false);
    },
    toggleMenu() {
      this.showMenu = !this.showMenu;
    },
  },
};
</script>
