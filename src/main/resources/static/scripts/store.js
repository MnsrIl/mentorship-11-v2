let store = {
    authenticatedUser: null,
    users: [],
    selectedUser: null,
};

const getState = () => ({
    ...store
});

const getUserById = (id) => getState().users.find(user => user.id === id);

const getUsers = () => getState().users;

const getAuthUser = () => getState().authenticatedUser;

const getSelectedUser = () => getState().selectedUser;

const setSelectedUser = (user) => {
    store = {...store, selectedUser: user};
}

const createUser = (user) => {
    store = {...store, users: [...store.users, user]}
}

const removeUser = (id) => {
    store = {...store, users: store.users.filter(user => user.id !== id)}
}

const updateUser = (user) => {
    store = {
        ...store,
        users: store.users.map(u => {
            if (u.id === user.id)
                return user;

            return u;
        }),
    }
}

const setAuthUser = (user) => {
    store = {...store, authenticatedUser: user};
}

const setUsersList = (data) => {
    store = {...store, users: data}
}