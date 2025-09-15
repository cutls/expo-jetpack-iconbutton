# expo-jetpack-iconbutton

Add [Jetpack IconButton](https://developer.android.com/develop/ui/compose/components/icon-button) to React Native Expo.

expo-jetpack-iconbutton

You should use not Expo Go, Development Build

## Install

```
yarn add expo-jetpack-iconbutton
```

You must make Development Build after install.

## Usage

```
import { IconButton } from 'expo-jetpack-iconbutton/jetpack-compose'
<IconButton variant="default" systemImage="filled.Menu" />
```

The same API as [Expo UI](https://docs.expo.dev/versions/latest/sdk/ui/#button-1).

### Icon

You can choose `drawable`(primary) or `systemImage`(secondary).

`drawable`: resource put in `/android/app/src/main/res/drawable`. `/android/app/src/main/res/drawable/ic_setting.png`->`<IconButton drawable="ic_setting" />`

or

`systemImage`: check: [src/jetpack-compose/](https://github.com/cutls/expo-jetpack-iconbutton/blob/master/src/jetpack-compose/IconButton/types.ts). `${Variant}.${Icon}` ex: `filled.List`

### `variant`

'default' | 'bordered' |'outlined'

