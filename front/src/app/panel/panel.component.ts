import { Component ,inject,signal} from '@angular/core';
import { ButtonModule } from "primeng/button";
import { CardModule } from "primeng/card";

import { DataViewModule } from 'primeng/dataview';
import { DialogModule } from 'primeng/dialog';
import { CommonModule } from '@angular/common';
import { SharedModule } from "app/shared/sharedModule";
import { Product } from 'app/products/data-access/product.model';
import { ProductsService } from 'app/products/data-access/products.service';
import { PanelService } from './data-access/panel.service';
@Component({
  selector: 'app-panel',
  standalone: true,
  imports: [SharedModule,CommonModule,DataViewModule, CardModule, ButtonModule, DialogModule],
  templateUrl: './panel.component.html',
  styleUrl: './panel.component.css'
})
export class PanelComponent {
  private  panelService = inject(PanelService);
  public panelProducts = this.panelService.panelProducts;


  get productEntries(): [any, number][] {
    const xx  =  Array.from(this.panelProducts().entries());
    return xx;
  }
  public RemoveFromPanel(product:Product){
        this.panelService.RemoveFromPanel(product);
  }

  public ReduceOneFromPanel(product:Product){
    this.panelService.ReduceOneFromPanel(product);
}
}
