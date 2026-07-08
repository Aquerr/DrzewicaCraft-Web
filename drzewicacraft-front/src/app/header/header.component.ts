import {ChangeDetectionStrategy, Component} from '@angular/core';

@Component({
  selector: 'dc-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.scss'],
  changeDetection: ChangeDetectionStrategy.OnPush
})
export class HeaderComponent {

  scrollToContact() {
    document.getElementById("footer")!.scrollIntoView({behavior: "smooth"});
  }
}
