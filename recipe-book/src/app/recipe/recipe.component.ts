import { Component, OnInit } from '@angular/core';
import { UserRedistrationService } from '../user-redistration.service';
import { ElementService } from '../element.service';
import { Router } from '@angular/router';
import { map } from 'rxjs/operators'
import { Element } from '../element'
import { Email } from '../email';

@Component({
  selector: 'app-recipe',
  templateUrl: './recipe.component.html',
  styleUrls: ['./recipe.component.css']
})
export class RecipeComponent implements OnInit {
  allRecipe: Element[];

  constructor(private userService: UserRedistrationService, private recipeService: ElementService, private router: Router) { }

  ngOnInit(): void {
    this.getAllMyRecipe();
  }

  getAllMyRecipe() {

  }
}
