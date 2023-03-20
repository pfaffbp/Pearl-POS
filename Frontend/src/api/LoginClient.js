class LoginClient {
    constructor(baseUrl) {
        this.baseUrl = baseUrl;
    }

    async login(email, password) {
        const response = await fetch(`${this.baseUrl}/login`, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({ email, password })
        });

        if (!response.ok) {
            const error = await response.json();
            throw new Error(error.message);
        }

        const result = await response.json();
        return result.token;
    }
}
