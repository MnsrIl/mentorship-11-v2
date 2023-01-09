async function configureCreatingForm(list) {
    const createForm = document.getElementById("createForm");

    createForm.addEventListener("submit", handleCreateForm(list, createForm));
}

function handleCreateForm (list, form) {
    return async function (event) {
        event.preventDefault();

        const response = await fetch("api/admin/create", {
            body: JSON.stringify(collectFormPayload(form)),
            method: "POST",
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json',
                'Referer': null
            }
        });

        const data = await response.json();

        if (!response.ok) return;

        createUser(data);

        list.append(generateRow(data, true));

        form.reset();
    }
}

function collectFormPayload(form) {
    const payload = {};

    for (const element of form.elements) {
        if (!element.name) continue;

        if (element.name !== "roles")
            payload[element.name] = element.value;
        else {
            const options = element.selectedOptions;

            const selectedIDs = [];

            for (let index = 0; index < options.length; index++) {
                const item = options.item(index);

                if (item) selectedIDs.push(item.value);
            }

            payload["authorities"] = selectedIDs.map(id => authorities.find(role => role.id === Number(id)));
        }
    }

    return payload;
}