import React from 'react';
import { View, Text, StyleSheet } from 'react-native';

export default function Contos() {
  return (
    <View style={styles.container}>
      <Text style={styles.title}>Aria de livros/ não disponível.</Text>
      {/* Conteúdo de contos */}
    </View>
  );
}

const styles = StyleSheet.create({
  container: { flex: 1, alignItems: 'center', justifyContent: 'center' },
  title: { fontSize: 24, fontWeight: 'bold' },
});
