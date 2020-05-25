import { Routes, RouterModule } from '@angular/router';
import { NgModule } from '@angular/core';
import { RegistrationComponent } from './registration/registration.component';
import { LogInComponent } from './log-in/log-in.component';
import { DetailsComponent } from './details/details.component';
import { AddRecipeComponent } from './add-recipe/add-recipe.component';
import { RecipeComponent } from './recipe/recipe.component';

const routes: Routes = [
    { path: "register", component: RegistrationComponent },
    { path: "Log-in", component: LogInComponent },
    { path: "updateDetails", component: DetailsComponent },
    { path: "addRecipe", component: AddRecipeComponent },
    { path: "My-Recipe", component: RecipeComponent }
];

@NgModule({
    imports: [RouterModule.forRoot(routes)],
    exports: [RouterModule]
})
export class AppRoutingModule {

}

