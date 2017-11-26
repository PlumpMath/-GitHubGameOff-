package de.sakul6499.githubgameoff.game

import de.sakul6499.githubgameoff.engine.graphics.Renderable
import de.sakul6499.githubgameoff.engine.graphics.Updateable
import de.sakul6499.githubgameoff.engine.maths.BoxVector
import de.sakul6499.githubgameoff.engine.maths.Vector2I

abstract class UIButton(val vb: BoxVector<Vector2I> = BoxVector(Vector2I(), Vector2I())) : Updateable, Renderable