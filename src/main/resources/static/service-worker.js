console.log('Hello from service-worker.js');

var CACHE_NAME = 'my-site-cache-v1';
var urlsToCache = [
  '/',
  '/styles/main.css',
  '/script/main.js',
  'https://health-pwa.herokuapp.com/'
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

withPWA({
        pwa: {
            dest: 'public',
            publicExcludes: [
                '!robots.txt',
                '!sitemap.xml.gz',
            ],
        },
    }),




importScripts('https://storage.googleapis.com/workbox-cdn/releases/3.6.3/workbox-sw.js');

workbox.routing.registerRoute(
  new RegExp('\.png$'),
  workbox.strategies.cacheFirst({
    cacheName: 'images-cache',
  })
);