import {enableProdMode, importProvidersFrom, provideZonelessChangeDetection} from "@angular/core";
import {environment} from "@environments/environment";
import {bootstrapApplication} from "@angular/platform-browser";
import {AppComponent} from "@app/app.component";
import {provideHttpClient, withInterceptorsFromDi, withXhr} from "@angular/common/http";
import {AppRoutingModule} from "@app/app-routing.module";

if (environment.production) {
  enableProdMode();
}

bootstrapApplication(AppComponent, {
  providers: [
    provideZonelessChangeDetection(),
    importProvidersFrom(AppRoutingModule),
    provideHttpClient(withXhr(), withInterceptorsFromDi())
  ]
}).catch((err) => console.error(err));
