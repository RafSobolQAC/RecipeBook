package controllers

import javax.inject._
import models.Recipe
import play.api.mvc._

import scala.collection.mutable.ListBuffer

@Singleton
class RecipeController @Inject()(cc: ControllerComponents) extends AbstractController(cc) with play.api.i18n.I18nSupport {
  def showRecipeForm(): Action[AnyContent] = Action { implicit request: Request[AnyContent] =>
    Ok(views.html.submitRecipe(Recipe.createRecipeForm, Recipe.recipeList)())
  }

  def submitRecipe(): Action[AnyContent] = Action { implicit request: Request[AnyContent] =>
    Recipe.createRecipeForm.bindFromRequest.fold({ formWithErrors =>
      BadRequest(views.html.submitRecipe(formWithErrors, Recipe.recipeList)())
    }, { recipe =>
      Recipe.recipeList = addRecipe(Recipe.recipeList, recipe)
      Ok(views.html.submitRecipe(Recipe.createRecipeForm, Recipe.recipeList)())
    })
  }



  def addRecipe(recipeList: ListBuffer[Recipe], recipe: Recipe): ListBuffer[Recipe] = {
    recipeList += recipe
    recipeList
  }

}
