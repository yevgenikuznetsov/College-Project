import { Component, OnInit } from '@angular/core';
import { UserRedistrationService } from '../user-redistration.service';
import { Router } from '@angular/router';
import { Element } from '../element'
import { ElementService } from '../element.service';
import { isEmpty } from 'rxjs/operators';


@Component({
  selector: 'app-add-recipe',
  templateUrl: './add-recipe.component.html',
  styleUrls: ['./add-recipe.component.css']
})
export class AddRecipeComponent implements OnInit {
  recipe: Element;
  ingridient: Element;
  name: string;
  element: Element;
  count: number;
  image: string;
  step: string;
  ingridientName: string;
  ingridientAmount: string;
  attributeRecipe: Map<string, string> = new Map<string, string>();
  attributeingridient: Map<string, string> = new Map<string, string>();




  constructor(private userservice: UserRedistrationService, private elementService: ElementService, private router: Router) {
    this.recipe = new Element();
  }

  ngOnInit(): void {
    this.count = 1;
    this.element = new Element();
  }

  onSubmitinfro() {
    this.attributeRecipe.set("image", this.image);
  }

  onSubmitAddIngredient() {
    this.attributeingridient.set(this.ingridientName, this.ingridientAmount);
  }

  onSubmitAddAttribute() {

    this.attributeRecipe.set("" + this.count, this.step);
    this.count++;
    this.step = '';
  }

  onSubmitFinish() {
    this.saveRecipeInDB();
    this.saveIngridientInDB();
  }

  saveRecipeInDB() {
    this.recipe.createBy.email = this.userservice.emailName;
    this.recipe.name = this.name;
    this.recipe.type = "recipe";
    this.recipe.active = true;

    this.attributeRecipe.forEach((value, key) => {
      this.recipe.elementAttributes[key] = value;
    })

    this.elementService.postElement(this.recipe, this.userservice.emailName).subscribe(resule => {
      console.log("hey");
    });
  }

  saveIngridientInDB() {

    this.attributeingridient.forEach((value, key) => {
      this.findIngridientByName(key, value);
    });
  }

  newIngridient(amount: string, name: string) {
    var ingridient: Element = new Element();
    ingridient.type = "ingridient";
    ingridient.name = name;
    ingridient.createBy.email = this.userservice.emailName;
    ingridient.active = true;

    ingridient.elementAttributes[this.userservice.emailName] = amount;

    this.elementService.postElement(ingridient, this.userservice.emailName).subscribe(resule => {
      console.log("hey");
    });
  }

  findIngridientByName(name: string, amount: string) {

    this.elementService.getElementByName(this.userservice.emailName, name)
      .then(data => {
        this.element = JSON.parse(JSON.stringify(data));
        console.log(this.element);
      }
      )
      .then(response => {
        if (Object.keys(this.element).length == 0) {
          this.newIngridient(amount, name);

          this.elementService.getElementByName(this.userservice.emailName, name)
            .then(data => {
              this.element = JSON.parse(JSON.stringify(data))
              console.log(this.element);
            })
        }
        else {
          console.log("dd")
          for (var value in this.element.elementAttributes) {
            this.attributeingridient.set(value, this.element.elementAttributes[value]);
          }

          this.element.elementAttributes = {};
          this.attributeingridient.set(this.userservice.emailName, this.ingridientAmount);

          this.attributeingridient.forEach((value, key) => {
            this.element.elementAttributes[key] = value;
          })

          this.attributeingridient.clear();
        }
      /*  
        else {
          

          for (var value in this.element.elementAttributes) {
            this.attributeingridient.set(value, this.element.elementAttributes[value]);
          }
          this.element.elementAttributes = {};
          this.attributeingridient.set(this.userservice.emailName, this.ingridientAmount);

          this.attributeingridient.forEach((value, key) => {
            this.element.elementAttributes[key] = value;
          })

           this.attributeingridient.clear();
        }
     */  })
   /*   .then(response => {
        console.log(this.element);
           })
          */  }



}

