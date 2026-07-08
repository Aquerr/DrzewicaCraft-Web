import {ChangeDetectionStrategy, Component} from '@angular/core';
import {faFacebook} from "@fortawesome/free-brands-svg-icons";
import {FaIconComponent} from "@fortawesome/angular-fontawesome";

@Component({
  selector: 'dc-footer',
  templateUrl: './footer.component.html',
  styleUrls: ['./footer.component.scss'],
  imports: [
    FaIconComponent
  ],
  changeDetection: ChangeDetectionStrategy.OnPush
})
export class FooterComponent {
  dateNow: number = new Date().getFullYear();
  facebookIcon = faFacebook;

}
