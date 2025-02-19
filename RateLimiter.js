A rate limiter restricts the number of requests a client can make within a specified time window. 
Here's a simple implementation:
class RateLimiter {
    constructor(maxRequests, windowSize) {
        this.maxRequests = maxRequests;
        this.windowSize = windowSize; // in milliseconds
        this.requests = [];
    }

    async canMakeRequest() {
        const now = Date.now();
        this.requests = this.requests.filter(time => now - time < this.windowSize);

        if (this.requests.length < this.maxRequests) {
            this.requests.push(now);
            return true;
        }
        return false;
    }
}

// Example usage
(async () => {
    const rateLimiter = new RateLimiter(3, 60000); // 3 requests per minute

    for (let i = 0; i < 5; i++) {
        const allowed = await rateLimiter.canMakeRequest();
        console.log(`Request ${i + 1} allowed: ${allowed}`);
    }

    // Simulate waiting for the next time window
    setTimeout(async () => {
        for (let i = 0; i < 5; i++) {
            const allowed = await rateLimiter.canMakeRequest();
            console.log(`Request ${i + 6} allowed: ${allowed}`);
        }
    }, 60000);
})();
