import { Routes } from "@angular/router";
import { HomeComponent } from "./shared/features/home/home.component";
import { PanelComponent } from "./panel/panel.component";

export const APP_ROUTES: Routes = [
  {
    path: "home",
    component: HomeComponent,
  },
  {
    path: "products",
    loadChildren: () =>
      import("./products/products.routes").then((m) => m.PRODUCTS_ROUTES)
  },{
    path: "panel",
    component: PanelComponent,
  },
  { path: "", redirectTo: "home", pathMatch: "full" },
];
