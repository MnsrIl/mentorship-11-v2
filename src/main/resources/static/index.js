document.addEventListener("DOMContentLoaded", async () => {
    const list = document.getElementById("users-table-body");

    // authorizing user
    await authorizeUser();

    // getting users list
    await loadUsers(list);

    // creating form submit
    await configureCreatingForm(list);

    await configureDeleteModal();

    await configureEditModal();
});

const authorities = [
    {
        id: 1,
        authority: "ROLE_USER"
    },
    {
        id: 2,
        authority: "ROLE_ADMIN"
    }
];

const getPlainRoles = ({authorities}) => authorities
    .map(({authority}) => authority.substring(5))
    .sort()
    .join(" ");

function generateRow(user, withActions = false) {
    const row = document.createElement("tr");
    row.id = `user_${user.id}-row`

    const [idCell, lastNameCell, firstnameCell, ageCell, emailCell, rolesCell] = Array.from({length: 6}, () => document.createElement("td"));

    idCell.textContent = user.id;
    lastNameCell.textContent = user.lastName;
    firstnameCell.textContent = user.firstName;
    ageCell.textContent = user.age;
    emailCell.textContent = user.email;
    rolesCell.textContent = getPlainRoles(user);

    row.append(idCell, lastNameCell, firstnameCell, ageCell, emailCell, rolesCell);

    if (withActions) {
        const [editActionCell, deleteActionCell] = generateActions(user);

        row.append(editActionCell, deleteActionCell);
    }

    return row;
}

function generateActions(user) {
    const editActionCell = document.createElement("td");
    const deleteActionCell = document.createElement("td");

    const editBtn = document.createElement("button");
    const deleteBtn = document.createElement("button");

    // edit
    editBtn.setAttribute("data-bs-toggle", "modal");
    editBtn.setAttribute("data-bs-target", "#editModal");

    editBtn.classList.add("btn", "btn-primary");

    editBtn.type = "button";
    editBtn.id = "edit_btn_" + user.id;
    editBtn.textContent = "Edit"

    editActionCell.append(editBtn);

    // delete
    deleteBtn.setAttribute("data-bs-toggle", "modal");
    deleteBtn.setAttribute("data-bs-target", "#deleteModal");

    deleteBtn.classList.add("btn", "btn-danger");

    deleteBtn.type = "button";
    deleteBtn.id = "delete_btn_" + user.id;
    deleteBtn.textContent = "Delete"

    deleteActionCell.append(deleteBtn);

    return [editActionCell, deleteActionCell];
}