import {ChangeDetectionStrategy, Component} from '@angular/core';
import {HeaderComponent} from "@app/header/header.component";
import {RouterLink, RouterOutlet} from "@angular/router";
import {FooterComponent} from "@app/footer/footer.component";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss'],
  changeDetection: ChangeDetectionStrategy.OnPush,
  imports: [
    HeaderComponent,
    RouterOutlet,
    FooterComponent,
    RouterLink
  ]
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
