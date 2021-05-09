console.log('Подключение сервис-воркера');

var CACHE_NAME = 'my-site-cache-v1';
var urlsToCache = [
    '/main',
    '/login',

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