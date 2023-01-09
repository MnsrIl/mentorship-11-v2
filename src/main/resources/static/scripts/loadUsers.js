async function loadUsers (list) {
    const response = await fetch("/api/admin");
    const data = await response.json();

    setUsersList(data);

    for (const user of getUsers()) {
        list.append(generateRow(user, true));
    }

    return data;
}