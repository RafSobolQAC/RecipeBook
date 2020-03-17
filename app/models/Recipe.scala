package models

import play.api.data.Form
import play.api.data.Forms._

import scala.collection.mutable.ListBuffer

case class Recipe(dish: String, ingredients: List[String], instructions: Option[String]) {
  override def toString: String = s"$dish: \n---------------------------------------\nIngredients: ${ingredients}\n-----------------------------\nInstructions: ${instructions.getOrElse("No instructions")}"
}



object Recipe {
  var recipeList: ListBuffer[Recipe] = new ListBuffer
  val createRecipeForm: Form[Recipe] = Form(
    mapping(
      "dish" -> nonEmptyText,
      "ingredients" -> list(text),
      "instructions" -> optional(text)
    )(Recipe.apply)(Recipe.unapply)
  )
}
