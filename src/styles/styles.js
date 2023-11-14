import default_theme from './theme/default'
import dark_theme from './theme/dark'

var dark_theme_on = Boolean(localStorage.getItem('dark_theme_on'));

export const styles = dark_theme_on ? [...default_theme, ...dark_theme] : default_theme
export function setTheme() {
    dark_theme_on = !dark_theme_on
    localStorage.setItem('dark_theme_on', dark_theme_on)
    window.location.reload()
}