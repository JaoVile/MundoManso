import React from 'react';
import { View, Text, StyleSheet, ImageBackground } from 'react-native';

export default function Brincadeiras() {
  return (
    
    <View style={styles.container}>
      <Text style={styles.title}>Área de Brincadeiras/ não disponível.</Text>
      {/* Conteúdo de brincadeiras */}
    </View>
  );
}

const styles = StyleSheet.create({
  container: { flex: 1, alignItems: 'center',justifyContent: "center"},
  title: { fontSize: 20, fontWeight: 'bold' },
});
