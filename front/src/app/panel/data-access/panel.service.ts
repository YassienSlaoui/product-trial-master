import { Injectable , signal  } from '@angular/core';
import { Product } from 'app/products/data-access/product.model';

@Injectable({
  providedIn: 'root'
})
export class PanelService {
  public panelProducts = signal<Map<Product, number>>(new Map<Product, number>());

   public AddToPanel(product: Product) {
        let currentMap = this.panelProducts(); 
        if (currentMap.has(product)) {
            const currentValue = currentMap.get(product) || 0;
            currentMap.set(product, currentValue + 1);
        } else {
          currentMap.set(product, 1);
        }
        this.panelProducts.set(new Map(currentMap));
      }

      public RemoveFromPanel(product: Product) {
        let currentMap = this.panelProducts(); 
        currentMap.delete(product);
        this.panelProducts.set(new Map(currentMap));
      }
      public ReduceOneFromPanel(product: Product) {
        let currentMap = this.panelProducts(); 
        const currentValue = currentMap.get(product) || 0;

        currentValue == 1 ? currentMap.delete(product) : currentMap.set(product, currentValue - 1);
        this.panelProducts.set(new Map(currentMap));
      }
}
