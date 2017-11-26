@file:JvmName("Launcher")

package de.sakul6499.githubgameoff

import de.sakul6499.githubgameoff.engine.GameMain
import de.sakul6499.githubgameoff.engine.graphics.Screen
import de.sakul6499.githubgameoff.engine.maths.Vector2F
import de.sakul6499.githubgameoff.game.BackgroundLayer
import de.sakul6499.githubgameoff.game.UIBar
import de.sakul6499.githubgameoff.game.entity.BulletLayer
import de.sakul6499.githubgameoff.game.entity.Enemy
import de.sakul6499.githubgameoff.game.entity.EnemyLayer
import de.sakul6499.githubgameoff.game.entity.PlayerLayer

fun main(args: Array<String>) {
    Runtime.getRuntime().addShutdownHook(Thread({ GameMain.stop() }))

    Screen.registerLayer(UIBar)
    Screen.registerLayer(BackgroundLayer)
    Screen.registerLayer(PlayerLayer)
    Screen.registerLayer(EnemyLayer)
    Screen.registerLayer(BulletLayer)
    GameMain.start()

    EnemyLayer.enemies.add(Enemy(Vector2F(Screen.GetWidth() / 2, Screen.GetHeight() / 2), 64))
    EnemyLayer.enemies.add(Enemy(Vector2F(Screen.GetWidth() / 2, Screen.GetHeight() / 2), 64))
}
