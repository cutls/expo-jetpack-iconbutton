import { requireNativeView } from 'expo';
import { StyleSheet, Platform } from 'react-native';

import type { ButtonProps } from './types';
import type { ViewEvent } from '../../types';
import React from 'react';
/**
 * @hidden
 */
export type NativeButtonProps = Omit<
  ButtonProps,
  'role' | 'onPress' | 'systemImage' | 'drawable'
> & {
  systemImage?: string;
  drawable?: string;
} & ViewEvent<'onButtonPressed', void>;

// We have to work around the `role` and `onPress` props being reserved by React Native.
const ButtonNativeView: React.ComponentType<NativeButtonProps> = Platform.OS === 'android' ? requireNativeView(
  'ExpoIconButton',
  'IconButton'
) : null as any;

/**
 * @hidden
 */
export function transformButtonProps(props: ButtonProps): NativeButtonProps {
  const { onPress, systemImage, drawable, ...restProps } = props;
  return {
    ...restProps,
    systemImage,
    drawable,
    onButtonPressed: onPress,
    // @ts-expect-error
    modifiers: props.modifiers?.map((m) => m.__expo_shared_object_id__),
    elementColors: props.elementColors
      ? props.elementColors
      : props.color
        ? {
            containerColor: props.color,
          }
        : undefined,
  };
}

/**
 * Displays a native button component.
 */
export function IconButton(props: ButtonProps) {
  if (Platform.OS !== 'android') return null;
  // Min height from https://m3.material.io/components/buttons/specs, minWidth
  return (
    <ButtonNativeView
      {...transformButtonProps(props)}
      style={StyleSheet.compose({ minWidth: 40, minHeight: 40 }, props.style)}
    />
  );
}
