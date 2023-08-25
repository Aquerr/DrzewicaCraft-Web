import { Component } from '@angular/core';
import {faFacebook} from "@fortawesome/free-brands-svg-icons";

@Component({
  selector: 'dc-footer',
  templateUrl: './footer.component.html',
  styleUrls: ['./footer.component.css']
})
export class FooterComponent {
  dateNow: number = new Date().getFullYear();
  facebookIcon = faFacebook;

}
