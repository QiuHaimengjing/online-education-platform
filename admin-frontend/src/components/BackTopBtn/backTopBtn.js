export function initializeScrollButton() {
  const scrollBtn = document.querySelector('.scroll-to-top')
  window.addEventListener('scroll', function() {
    const n = this.document.documentElement.scrollTop
    scrollBtn.style.opacity = n >= 100 ? 1 : 0
  })
}
