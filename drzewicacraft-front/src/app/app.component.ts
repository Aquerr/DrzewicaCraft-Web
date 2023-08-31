import { Component } from '@angular/core';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  hamburgerMenuVisible: boolean = false;

  onCoverClick() {
    this.hamburgerMenuVisibilityChange();
  }

  hamburgerMenuVisibilityChange() {
    this.hamburgerMenuVisible = !this.hamburgerMenuVisible;
    document.getElementById("hamburger-menu-cover")!.classList.toggle("active");
    const hamburgerMenu = document.getElementById("hamburger-menu")!;
    hamburgerMenu.classList.toggle("active");
    document.documentElement.classList.toggle("noscroll");
    document.getElementsByTagName("body")[0].classList.toggle("noscroll");
  }

  hideHamburgerMenu() {
    this.hamburgerMenuVisibilityChange();
  }
}
