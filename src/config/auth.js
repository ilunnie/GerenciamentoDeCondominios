import api from "./api";

export async function verifyToken() {
    return new Promise(async (resolve) => {
        const body = {
            token: localStorage.getItem("token"),
        };

        try {
            const response = await api.post("jwt/validate", body, {
                headers: {
                    'Content-Type': 'application/json',
                },
            });

            resolve(response.data);
        } catch (error) {
            resolve(false);
        }
    });
}
