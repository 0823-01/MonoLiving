function openLoginPanel() {
    const loginPanel = document.querySelector('.login-panel');
    const overlay = document.querySelector('.overlay');
    loginPanel.classList.add('active');
    overlay.classList.add('active');
    overlay.addEventListener('click', closeLoginPanel);

}

function closeLoginPanel() {
    const loginPanel = document.querySelector('.login-panel');
    const overlay = document.querySelector('.overlay');
    loginPanel.classList.remove('active');
    overlay.classList.remove('active');
    
}

// Close button functionality
document.querySelector('.close-btn').addEventListener('click', closeLoginPanel);

function openSearchPanel() {
    document.querySelector('.search-panel').classList.add('open');
    document.querySelector('.overlay').classList.add('active');
}

function closeSearchPanel() {
    document.querySelector('.search-panel').classList.remove('open');
    document.querySelector('.overlay').classList.remove('active');
}

document.querySelector('.close-search-btn').addEventListener('click', closeSearchPanel);
document.querySelector('.overlay').addEventListener('click', closeSearchPanel);

function search() {
    const query = document.querySelector('.search-panel input').value;
    if (query) {
        console.log('Searching for:', query); 
    }
}

document.querySelector('.search-panel input').addEventListener('keydown', function(event) {
    if (event.key === 'Enter') {
        event.preventDefault();
        search(); 
    }
});

const menuImage = document.querySelector('#header1 img'); 
const menuPanel = document.querySelector('.menu-panel');
const closeMenuBtn = document.querySelector('.close-menu-btn');
const overlay = document.querySelector('.overlay'); 

menuImage.addEventListener('click', () => {
    menuPanel.classList.add('active'); 
    overlay.classList.add('active');  
});

const closeMenu = () => {
    menuPanel.classList.remove('active'); 
    overlay.classList.remove('active');  

    const submenuPanels = document.querySelectorAll('.submenu-panel');
    submenuPanels.forEach(submenu => {
        submenu.classList.remove('active'); 
        submenu.style.display = 'none'; 
    });
};

closeMenuBtn.addEventListener('click', closeMenu);

overlay.addEventListener('click', closeMenu);

const submenuPanels = document.querySelectorAll('.submenu-panel');

document.querySelectorAll('.menu-panel a').forEach(item => {
    item.addEventListener('click', function(event) {
        event.preventDefault();
        
        document.querySelectorAll('.submenu-panel').forEach(submenu => {
            submenu.style.display = 'none'; 
            submenu.querySelectorAll('li').forEach(li => {
                li.style.opacity = 0; 
            });
        });
        
        const targetId = this.getAttribute('data-target');
        const targetSubmenu = document.getElementById(targetId);
        
        if (targetSubmenu) {
            targetSubmenu.style.display = 'block'; 
            
            const items = targetSubmenu.querySelectorAll('li');
            items.forEach((item, index) => {
                setTimeout(() => {
                    item.style.opacity = 1;
                }, index * 100);
            });
        }
    });
});
