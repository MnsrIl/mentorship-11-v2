async function authorizeUser() {
    const aboutUserTBody = document.getElementById("user-information_table-body");
    const headerEmailBox = document.getElementById("header_user-email");
    const headerUserInfoBox = document.getElementById("header_user-info");
    const adminTab = document.getElementById("v-pills-admin");
    const userTab = document.getElementById("v-pills-user_details");
    const adminTabNavigator = document.getElementById("v-pills-admin-tab");
    const userTabNavigator = document.getElementById("v-pills-user_details-tab")


    const response = await fetch("api/auth");
    const authUser = await response.json();

    if (!authUser) return;

    setAuthUser(authUser);
    
    const authenticatedUser = getAuthUser();

    headerEmailBox.textContent = authenticatedUser.email;
    const plainRoles = getPlainRoles(authenticatedUser);
    headerUserInfoBox.append(plainRoles);


    aboutUserTBody.append(generateRow(authenticatedUser));

    // showing panel
    const isAdmin = authenticatedUser.authorities.some(role => role.authority === "ROLE_ADMIN");

    if (isAdmin) {
        adminTabNavigator.classList.remove("visually-hidden");
        adminTabNavigator.classList.add("active");
        adminTab.classList.add("show", "active");
        adminTabNavigator.ariaSelected = "true";
        userTabNavigator.ariaSelected = "false";
    } else {
        userTab.classList.add("show", "active");
        adminTabNavigator.ariaSelected = "false";
        userTabNavigator.ariaSelected = "true";
        userTabNavigator.classList.add("active");
        adminTabNavigator.remove();
    }
}