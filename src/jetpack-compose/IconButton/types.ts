import type { StyleProp, ViewStyle } from 'react-native';
import type { ExpoModifier } from '../../types';

type Variant = 'rounded' | 'twotone' | 'outlined' | 'filled' | 'sharp';

type Icon =
  | 'AccountBox'
  | 'AccountCircle'
  | 'Add'
  | 'AddCircle'
  | 'ArrowBack'
  | 'ArrowDropDown'
  | 'ArrowForward'
  | 'Build'
  | 'Call'
  | 'Check'
  | 'CheckCircle'
  | 'Clear'
  | 'Close'
  | 'Create'
  | 'DateRange'
  | 'Delete'
  | 'Done'
  | 'Edit'
  | 'Email'
  | 'ExitToApp'
  | 'Face'
  | 'Favorite'
  | 'FavoriteBorder'
  | 'Home'
  | 'Info'
  | 'KeyboardArrowDown'
  | 'KeyboardArrowLeft'
  | 'KeyboardArrowRight'
  | 'KeyboardArrowUp'
  | 'List'
  | 'LocationOn'
  | 'Lock'
  | 'MailOutline'
  | 'Menu'
  | 'MoreVert'
  | 'Notifications'
  | 'Person'
  | 'Phone'
  | 'Place'
  | 'PlayArrow'
  | 'Refresh'
  | 'Search'
  | 'Send'
  | 'Settings'
  | 'Share'
  | 'ShoppingCart'
  | 'Star'
  | 'ThumbUp'
  | 'Warning';

export type MaterialIcon = `${Variant}.${Icon}`;

/**
 * The built-in button styles available on Android.
 * - `outlined` - A button with an outline.
 * - `elevated` - A filled button with a shadow.
 */
export type ButtonVariant = 'default' | 'bordered' |'outlined';

/**
 * Colors for button's core elements.
 */
export type ButtonElementColors = {
  containerColor?: string;
  contentColor?: string;
  disabledContainerColor?: string;
  disabledContentColor?: string;
};

export type ButtonProps = {
  /**
   * A callback that is called when the button is pressed.
   */
  onPress?: () => void;
  /**
   * A string describing the system image to display in the button.
   * Uses Material Icons on Android.
   */
  systemImage?: MaterialIcon;
  /**
   * Or use drawable name from `android/app/src/main/res/drawable`.
   */
  drawable?: string;
  /**
   * The button variant.
   */
  variant?: ButtonVariant;
  /**
   * Additional styles to apply to the button.
   */
  style?: StyleProp<ViewStyle>;
  /**
   * Colors for button's core elements.
   * @platform android
   */
  elementColors?: ButtonElementColors;
  /**
   * Button color.
   */
  color?: string;
  /**
   * Disabled state of the button.
   */
  disabled?: boolean;

  /** Modifiers for the component */
  modifiers?: ExpoModifier[];
};
