package expo.modules.iconbutton.iconbutton

import android.content.Context
import android.graphics.Color
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.FilledTonalIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedIconButton
import expo.modules.kotlin.viewevent.EventDispatcher
import expo.modules.kotlin.views.ExpoComposeView
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import expo.modules.kotlin.AppContext
import expo.modules.kotlin.records.Field
import expo.modules.kotlin.records.Record
import expo.modules.kotlin.views.ComposeProps
import java.io.Serializable
import expo.modules.kotlin.types.Enumerable
import expo.modules.iconbutton.DynamicTheme
import expo.modules.iconbutton.ExpoModifier
import expo.modules.iconbutton.compose
import expo.modules.iconbutton.fromExpoModifiers
import expo.modules.iconbutton.getImageVector

open class ButtonPressedEvent() : Record, Serializable

enum class ButtonVariant(val value: String) : Enumerable {
  DEFAULT("default"),
  BORDERED("bordered"),
  OUTLINED("outlined")
}

class ButtonColors : Record {
  @Field
  val containerColor: Color? = null

  @Field
  val contentColor: Color? = null

  @Field
  val disabledContainerColor: Color? = null

  @Field
  val disabledContentColor: Color? = null
}

data class ButtonProps(
  val text: MutableState<String> = mutableStateOf(""),
  val variant: MutableState<ButtonVariant?> = mutableStateOf(ButtonVariant.DEFAULT),
  val elementColors: MutableState<ButtonColors> = mutableStateOf(ButtonColors()),
  val systemImage: MutableState<String?> = mutableStateOf(null),
  val drawable: MutableState<String?> = mutableStateOf(null),
  val disabled: MutableState<Boolean> = mutableStateOf(false),
  val modifiers: MutableState<List<ExpoModifier>> = mutableStateOf(emptyList())
) : ComposeProps

@Composable
fun StyledButton(variant: ButtonVariant, colors: ButtonColors, disabled: Boolean, onPress: () -> Unit, modifier: Modifier = Modifier, content: @Composable () -> Unit) {
  when (variant) {
    ButtonVariant.BORDERED -> FilledTonalIconButton(
      onPress,
      enabled = !disabled,
      content = content,
      colors = IconButtonDefaults.filledTonalIconButtonColors(
        containerColor = colors.containerColor.compose,
        contentColor = colors.contentColor.compose,
        disabledContainerColor = colors.disabledContainerColor.compose,
        disabledContentColor = colors.disabledContentColor.compose
      ),
      modifier = modifier
    )

    ButtonVariant.OUTLINED -> OutlinedIconButton(
      onPress,
      enabled = !disabled,
      content = content,
      colors = IconButtonDefaults.outlinedIconButtonColors(
        containerColor = colors.containerColor.compose,
        contentColor = colors.contentColor.compose,
        disabledContainerColor = colors.disabledContainerColor.compose,
        disabledContentColor = colors.disabledContentColor.compose
      ),
      modifier = modifier
    )

    else -> androidx.compose.material3.IconButton(
      onPress,
      enabled = !disabled,
      content = content,
      colors = IconButtonDefaults.iconButtonColors(
        containerColor = colors.containerColor.compose,
        contentColor = colors.contentColor.compose,
        disabledContainerColor = colors.disabledContainerColor.compose,
        disabledContentColor = colors.disabledContentColor.compose
      ),
      modifier = modifier
    )
  }
}

class IconButton(context: Context, appContext: AppContext) :
  ExpoComposeView<ButtonProps>(context, appContext, withHostingView = true) {
  override val props = ButtonProps()
  private val onButtonPressed by EventDispatcher<ButtonPressedEvent>()

  init {
    clipToPadding = false // needed for elevated buttons to work
    clipChildren = false
  }

  @Composable
  override fun Content(modifier: Modifier) {
    val (variant) = props.variant
    val (text) = props.text
    val (colors) = props.elementColors
    val (systemImage) = props.systemImage
    val (drawable) = props.drawable
    val (disabled) = props.disabled
    DynamicTheme {
      StyledButton(
        variant ?: ButtonVariant.DEFAULT,
        colors,
        disabled,
        onPress = { onButtonPressed.invoke(ButtonPressedEvent()) },
        modifier = Modifier.fromExpoModifiers(props.modifiers.value)
      ) {
        if (drawable != null) {
           Icon(
              painter = androidx.compose.ui.res.painterResource(
                context.resources.getIdentifier(
                  drawable,
                  "drawable",
                  context.packageName
                )
              ),
              contentDescription = drawable
            )
        } else {
          getImageVector(systemImage)?.let {
              Icon(
                it,
                contentDescription = systemImage
              )
            }
        }
      }
    }
  }
}
