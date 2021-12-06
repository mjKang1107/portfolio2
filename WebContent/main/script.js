// Get all of the images that are marked up to fade in
const images = document.querySelectorAll('.js-lazyload-image');

const sections = document.querySelectorAll('.section');

let config = {
  rootMargin: '0px',
  threshold: 0
};

let observer = new IntersectionObserver((entries) => {
    console.log(entries);
  entries.forEach(entry => {
    if (entry.isIntersecting) {
      intersectionHandler(entry);
    } 

  });
}, config);

sections.forEach(section => {
  observer.observe(section);
});

function intersectionHandler(entry) {
  const current = document.querySelector('.section.active');
  const next = entry.target;
  const header = next.querySelector(".section--header");

  if (current) {
    current.classList.remove('active');
  }
  if (next) {
    next.classList.add('active');
    document.body.style.setProperty("--color-bg", next.dataset.bgcolor);
  }
}

images.forEach(image => {
  observer.observe(image);
});

function preloadImage(img) {
  const src = img.getAttribute('data-src');
  if (!src) { return; }
  img.src = src;
}



// function onIntersection(entries) {
//   // Loop through the entries
//   for (var i = 0, len = entries.length; i < len; i++) {
//     // Are we in viewport?
//     if (entries[i].intersectionRatio > 0) {
//       console.log('in viewport');

//       // Stop watching and load the image
//       observer.unobserve(entries[i].target);
//       entries[i].target.classList.add('in-viewport');
//     }
//   }
// }