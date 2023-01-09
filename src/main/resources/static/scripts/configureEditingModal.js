async function configureEditModal() {
    const editModalElement = document.getElementById("editModal");
    const editFormElement = document.getElementById("editForm");
    const editCloseBtn = document.getElementById("edit_btn_close");

    editFormElement.addEventListener("submit", async (event) => {
        event.preventDefault();

        const payload = collectFormPayload(editFormElement);

        const response = await fetch("/api/admin/edit/", {
            method: "PUT",
            body: JSON.stringify(payload),
            headers: {
                "Content-Type": "application/json"
            }
        });

        const updatedUser = await response.json();

        if (response.ok) {
            const invokedUserRow = document.getElementById(`user_${payload.id}-row`);

            editCloseBtn.click();

            updateUser(updatedUser);

            invokedUserRow.replaceWith(generateRow(updatedUser, true));
            setSelectedUser(null);
        }
    })

    editModalElement.addEventListener("show.bs.modal", (event) => {
        const btn = event.relatedTarget;

        const invokedId = Number(btn.id.substring(9));
        const invokedUser = getUserById(invokedId);

        setSelectedUser(invokedUser);

        const formInputsIds = ["#editFormId", "#editFormFirstName", "#editFormLastName",
            "#editFormAge", "#editFormEmail"];

        const formInputs = formInputsIds.map(inputId => editModalElement.querySelector(inputId));

        formInputs.forEach(input => input.value = invokedUser[input.name]);
    });
}