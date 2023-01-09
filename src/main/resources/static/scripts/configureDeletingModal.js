async function configureDeleteModal() {
    const deleteModalElement = document.getElementById("deleteModal");

    const deleteSubmitBtn = document.getElementById("delete_btn_submit");
    const deleteCloseBtn = document.getElementById("delete_btn_close");

    deleteSubmitBtn.addEventListener("click", async () => {
        const invokedUser = getSelectedUser();

        const response = await fetch("/api/admin/delete/" + invokedUser.id, {
            method: "DELETE",
        });

        if (response.ok) {
            const invokedUserRow = document.getElementById(`user_${invokedUser.id}-row`);

            deleteCloseBtn.click();

            console.log(getState().users.length, getState().users);
            removeUser(invokedUser.id);
            console.log(getState().users.length, getState().users);

            invokedUserRow.remove();
            setSelectedUser(null);
        }
    })

    deleteModalElement.addEventListener("show.bs.modal", (event) => {
        const btn = event.relatedTarget;

        const invokedId = Number(btn.id.substring(11));
        const invokedUser = getUserById(invokedId);

        setSelectedUser(invokedUser);

        const formInputsIds = ["#deleteFormId", "#deleteFormFirstName", "#deleteFormLastName",
            "#deleteFormAge", "#deleteFormEmail"];

        const formInputs = formInputsIds.map(inputId => deleteModalElement.querySelector(inputId));

        formInputs.forEach(input => input.value = invokedUser[input.name]);

        const rolesSelect = deleteModalElement.querySelector("#deleteFormRoles");

        const rolesOptions = invokedUser.authorities.map(r => {
            const option = document.createElement("option");
            option.value = r.id;
            option.textContent = r.authority;

            return option;
        });

        rolesSelect.textContent = "";

        rolesSelect.append(...rolesOptions);
    });
}