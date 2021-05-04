console.log('Hello from service-worker.js');

var CACHE_NAME = 'my-site-cache-v1';
var urlsToCache = [
    'https://health-pwa.herokuapp.com/',
    '/main',
    '/login',
    '/registration',
    '/user'
];

self.addEventListener('install', function(event) {
    // Perform install steps
    event.waitUntil(
        caches.open(CACHE_NAME)
        .then(function(cache) {
            console.log('Opened cache');
            return cache.addAll(urlsToCache);
        })
    );
});

self.addEventListener('fetch', event => {
    // Respond with a cached resource, or else fetch from network.
    event.respondWith((async() => {
        const response = await caches.match(event.request);
        return response || fetch(event.request);
    })());
});